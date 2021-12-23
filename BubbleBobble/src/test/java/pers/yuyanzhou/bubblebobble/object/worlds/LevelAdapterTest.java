package pers.yuyanzhou.bubblebobble.object.worlds;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 *Test level adapter
 */
class LevelAdapterTest {

    @Test
    void getMapPath() throws IOException {
        LevelAdapter adapter = new LevelAdapter(1);
        assertEquals(getClass().getResourceAsStream("/world/World1.txt").read(),adapter.getMapPath().read());
    }
}