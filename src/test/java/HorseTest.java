import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class HorseTest {
    @Test
    void constructorFirstParameterNullReturnException(){
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse(null, 0, 0);
                });
    }

    @Test
    void constructorFirstParameterNullReturnTextMessage(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse(null, 0, 0);
        });
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = { " ", "\t" })
    void constructorFirstParametersSpaceCharactersReturnException(String argument){
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse(argument, 0, 0);
                });
    }

    @ParameterizedTest
    @ValueSource(strings = { " ", "\t" })
    void constructorFirstParametersSpaceCharactersReturnTextMessage(String argument){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse(argument, 0, 0);
                });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructorSecondParameterNegativeNumberReturnException(){
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse("Name", -1, 0);
                });
    }

    @Test
    void constructorSecondParameterNegativeNumberReturnTextMessage(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse("Name", -1, 0);
                });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorThirdParameterNegativeNumberReturnException(){
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse("Name", 0, -1);
                });
    }

    @Test
    void constructorThirdParameterNegativeNumberReturnTextMessage(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse("Name", 0, -1);
                });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName(){
        Horse horse = new Horse("Name", 0, 0);
        assertEquals("Name", horse.getName());
    }

    @Test
    void getSpeed(){
        Horse horse = new Horse("Name", 10, 0);
        assertEquals(10, horse.getSpeed());
    }

    @Test
    void getDistance(){
        Horse horse = new Horse("Name", 10, 30);
        assertEquals(30, horse.getDistance());
    }

    @Test
    void getDistanceForTwoParameterConstructor(){
        Horse horse = new Horse("Name", 10);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void moveGetRandomDouble(){
        try(MockedStatic<Horse> mockStatic = mockStatic(Horse.class)){
            Horse horse = new Horse("Name", 10, 10);
            horse.move();
            mockStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9), times(1));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "5.0, 0.2, 1.0",
            "10.0, 0.5, 5.0",
            "15.0, 0.9, 13.5"
    })
    void moveGetRandomDoubleDistanceValue(double speed, double randomValue, double expectedIncrease){
        try(MockedStatic<Horse> mockStatic = mockStatic(Horse.class)){
            Horse horse = new Horse("Name", speed);
            mockStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);
            horse.move();
            assertEquals(expectedIncrease, horse.getDistance(), 0.0001);
        }
    }
}
