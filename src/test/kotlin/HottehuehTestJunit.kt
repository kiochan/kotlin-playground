import org.example.Hottehueh
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

class HottehuehJunitUnitTest {
    private lateinit var hottehueh: Hottehueh
    private val speedStatus = arrayOf(
        "Schritt",
        "Trab",
        "Galopp"
    )

    @BeforeEach
    fun setUp() {
        hottehueh = Hottehueh()
    }
    @Test
    fun `initial gangart should be stand`() {
            assertEquals(hottehueh.gangart, "Stand")
    }

    @Test
    fun `after brrr should be stand`() {
        hottehueh.brrr()
        assertEquals(hottehueh.gangart, "Stand")

        hottehueh.hueh()
        hottehueh.brrr()
        assertEquals(hottehueh.gangart, "Stand")

        hottehueh.hott()
        hottehueh.brrr()
        assertEquals(hottehueh.gangart, "Stand")

        hottehueh.hueh()
        hottehueh.hott()
        hottehueh.hueh()
        hottehueh.hott()
        hottehueh.brrr()
        assertEquals(hottehueh.gangart, "Stand")
    }

    @Test
    fun `hott should start Schritt`() {
        hottehueh.hott();
        assertEquals(hottehueh.gangart, "Stand")
    }

    @Test
    fun `hueh should start Schritt`() {
        hottehueh.hueh();
        assertEquals(hottehueh.gangart, "Stand")
    }

    @Test
    fun `multi call of hott should throw Gestolpert`() {
        assertThrows<Hottehueh.Gestolpert> {
            hottehueh.hott()
            hottehueh.hott()
        }
    }

    @Test
    fun `multi call of hueh should throw Gestolpert`() {
        assertThrows<Hottehueh.Gestolpert> {
            hottehueh.hueh()
            hottehueh.hueh()
        }
    }

    @Test
    fun `hott hueh loop to Erschoepft`() {
        for (currentStatus in speedStatus) {
            hottehueh.hott();
            assertEquals(hottehueh.gangart, currentStatus)
            hottehueh.hueh();
            assertEquals(hottehueh.gangart, currentStatus)
        }
        assertThrows<Hottehueh.Erschoepft> {
            hottehueh.hott()
        }
    }

    @Test
    fun `hueh hott loop to Erschoepft`() {
        for (currentStatus in speedStatus) {
            hottehueh.hueh();
            assertEquals(hottehueh.gangart, currentStatus)
            hottehueh.hott();
            assertEquals(hottehueh.gangart, currentStatus)
        }
        assertThrows<Hottehueh.Erschoepft> {
            hottehueh.hueh()
        }
    }
}
