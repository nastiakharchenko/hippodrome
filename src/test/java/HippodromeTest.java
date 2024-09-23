import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class HippodromeTest {
    @Test
    void constructorFirstParameterNullReturnException(){
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Hippodrome(null);
                });
    }

    @Test
    void constructorFirstParameterNullReturnTextMessage(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new Hippodrome(null);
                });
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorFirstParameterEmptyReturnException(){
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Hippodrome(new ArrayList<>());
                });
    }

    @Test
    void constructorFirstParameterNullEmptyTextMessage(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new Hippodrome(new ArrayList<>());
                });
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, i + 1, i + 1));
        }
        Hippodrome h = new Hippodrome(horses);
        assertEquals(horses, h.getHorses());
    }

    @Test
    void move(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horse = mock(Horse.class);
            horses.add(horse);
        }
        Hippodrome h = new Hippodrome(horses);
        h.move();
        for (Horse horse : horses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinner(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, i + 1, i + 1));
        }
        Hippodrome h = new Hippodrome(horses);
        assertEquals(horses.get(29), h.getWinner());
    }
}
