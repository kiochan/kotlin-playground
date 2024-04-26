import io.kotest.matchers.shouldBe
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec.*
import org.example.Hottehueh

class HottehuehKotestUnitTest {
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
        with(hottehueh) {
            gangart shouldBe "Stand"
        }
    }

    @Test
    fun `after brrr should be stand`() {
        with(hottehueh) {
            brrr()
            gangart shouldBe "Stand"

            hueh()
            brrr()
            gangart shouldBe "Stand"

            hott()
            brrr()
            gangart shouldBe "Stand"

            hueh(); hott()
            hueh(); hott()
            brrr()
            gangart shouldBe "Stand"
        }
    }

    @Test
    fun `hott should start Schritt`() {
        with(hottehueh) {
            hott(); gangart shouldBe "Schritt"
        }
    }

    @Test
    fun `hueh should start Schritt`() {
        with(hottehueh) {
            hueh(); gangart shouldBe "Schritt"
        }
    }

    @Test
    fun `multi call of hott should throw Gestolpert`() {
        with(hottehueh) {
            hott();
            shouldThrow<Hottehueh.Gestolpert> {
                hott()
            }
        }
    }

    @Test
    fun `multi call of hueh should throw Gestolpert`() {
        hottehueh.hueh()
        shouldThrow<Hottehueh.Gestolpert> {
            hottehueh.hueh()
        }
    }

    @Test
    fun `hott hueh loop to Erschoepft`() {
        with(hottehueh) {
            gangart shouldBe "Stand"
            for (currentStatus in speedStatus) {
                hott(); gangart shouldBe currentStatus
                hueh(); gangart shouldBe currentStatus
            }
            shouldThrow<Hottehueh.Erschoepft> {
                hott()
            }
        }
    }

    @Test
    fun `hueh hott loop to Erschoepft`() {
        with(hottehueh) {
            gangart shouldBe "Stand"
            for (currentStatus in speedStatus) {
                hueh(); gangart shouldBe currentStatus
                hott(); gangart shouldBe currentStatus
            }
            shouldThrow<Hottehueh.Erschoepft> {
                hueh()
            }
        }
    }
}
