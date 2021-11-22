import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PermissionManagerTest {
    PermissionManager permissionManager;

    @BeforeEach
    void initialize() {
        permissionManager = new PermissionManager();
    }

    @Test
    void getmCurrentLevel() {
        assertEquals("Developer", permissionManager.getmCurrentLevel());
    }

    @Test
    void setPermissionLevel() {
        permissionManager.setPermissionLevel(PermissionLevel.USER);
        assertEquals("User", permissionManager.getmCurrentLevel());
        permissionManager.setPermissionLevel(PermissionLevel.ADMIN);
        assertEquals("Admin", permissionManager.getmCurrentLevel());
        permissionManager.setPermissionLevel(PermissionLevel.DEVELOPER);
        assertEquals("Developer", permissionManager.getmCurrentLevel());
    }
}