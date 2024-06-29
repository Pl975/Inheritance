import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import ru.netology.Post
import ru.netology.WallService

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addPost() {
        val result = WallService.add(Post(0, "Good Morning")).id
        assertNotEquals(0, result)

    }

    @Test
    fun updateExisting() {
        WallService.add(Post(1, "Good Morning"))
        WallService.add(Post(2, "Good Evening"))
        val result = WallService.update(Post(1, "Good Night"))
        assertTrue(result)

    }


    @Test
    fun updateNotExisting() {
        val updatedPost = WallService.update(Post(5, "Good Evening"))
        assertFalse(updatedPost)
    }


}