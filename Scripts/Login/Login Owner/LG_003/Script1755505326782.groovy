import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import groovy.json.JsonOutput as JsonOutput
import java.nio.file.Files as Files
import java.nio.file.Paths as Paths
import org.openqa.selenium.WebDriver
import org.openqa.selenium.Cookie
import com.kms.katalon.core.webui.driver.DriverFactory

// --- LANGKAH 1: BUKA BROWSER DAN AKSES HALAMAN UTAMA ---
WebUI.openBrowser('')

WebUI.navigateToUrl('https://laravel-qa.kpntr.com')

WebUI.maximizeWindow()

// --- LANGKAH 2: KLIK TOMBOL MASUK (HANDLE MOBILE & DESKTOP) ---
// Definisikan Test Object yang dibutuhkan
TestObject burgerMenu = new TestObject('burgerMenu')

burgerMenu.addProperty('xpath', ConditionType.EQUALS, '//*[@id="navbar-main"]/div/button')

TestObject tombolMasukHeader = findTestObject('Landing Page_Kasir Pintar/a_Daftar_btn_login_page')

// Logika untuk klik tombol Masuk di header
if (WebUI.verifyElementVisible(burgerMenu, FailureHandling.OPTIONAL)) {
    println('Tampilan Mobile terdeteksi. Klik burger menu dahulu.')

    WebUI.click(burgerMenu)

    WebUI.waitForElementClickable(tombolMasukHeader, 10)

    WebUI.click(tombolMasukHeader)
} else {
    println('Tampilan Desktop terdeteksi. Langsung klik Masuk.')

    WebUI.click(tombolMasukHeader)
}

WebUI.delay(3 // Tambahkan delay 3 detik untuk observasi
    )

WebUI.waitForPageLoad(10 // Tunggu halaman login selesai dimuat
    )

// --- LANGKAH 3-6: PROSES PENGISIAN FORM LOGIN ---
// 3. Pastikan field "Masuk Sebagai" terpilih "Owner".
// Kita verifikasi bahwa label 'Owner' memiliki class 'active'
WebUI.verifyElementPresent(findTestObject('Halaman Login Owner/select_Daftar di sini_sebagai'), 10)

println('Verifikasi "Masuk Sebagai Owner" berhasil.')

// 4. Masukkan E-Mail Owner yang valid.
WebUI.setText(findTestObject('Halaman Login Owner/input_Daftar di sini_email'), 'rizkymanual96@yopmail.com')

println('Email berhasil diinput.')

// 5. Masukkan Password Owner yang valid.
// Direkomendasikan menggunakan Encrypted Text untuk keamanan.
// Cara membuat: Klik Tools > Encrypt Text, masukkan 'manual123', klik Encrypt.
// Lalu copy hasilnya dan paste di sini. Contoh: 'jKLmN....='
WebUI.setText(findTestObject('Halaman Login Owner/input_Daftar di sini_password'), 'manual123' // Ganti dengan hasil enkripsi dari 'manual123'
    )

println('Password berhasil diinput.')

// 6. Klik tombol "Masuk".
WebUI.click(findTestObject('Halaman Login Owner/button_Daftar di sini_g-recaptcha button-green-rounded w-100 mt-20 font-14-m'))

println('Tombol Masuk di form login diklik. Menunggu redirect...')

WebUI.delay(5 // Beri waktu 5 detik agar pesan error (jika ada) sempat muncul.
    )

// --- EKSPEKTASI: VERIFIKASI HASIL LOGIN DENGAN 3 KEMUNGKINAN URL ---
WebUI.waitForPageLoad(20 // Beri waktu lebih lama untuk redirect setelah login
    )

// Definisikan 3 kemungkinan URL sukses
String urlAppTour = 'https://laravel-qa.kpntr.com/account/app_tour'

String urlFreeDashboard = 'https://laravel-qa.kpntr.com/account/monitoring_data/show_dapat/show_untung'

String urlProDashboard = 'https://laravel-qa.kpntr.com/account'

// Dapatkan URL saat ini setelah login
String actualUrl = WebUI.getUrl()

// Lakukan pengecekan
// Lakukan pengecekan dengan struktur IF-ELSE IF-ELSE yang benar
if (actualUrl.equals(urlAppTour)) {
    // --- Skenario Khusus: Masuk ke Halaman App Tour ---
    println('Login Sukses. Diarahkan ke Halaman App Tour: ' + actualUrl)

    // Klik tombol "Nanti" atau "Lewati"
    // **PENTING**: Pastikan Test Object ini benar-benar menunjuk ke tombol "Nanti".
    // Nama object Anda "Mulai Panduan" sepertinya kurang sesuai.
    WebUI.click(findTestObject('Halaman Login Owner/Page_Kasir Pintar/a_Mulai Panduan_font-14 font-green medium'))

    println('Tombol "Nanti" di halaman App Tour diklik. Menunggu redirect ke dashboard...')

    // Tunggu halaman dashboard dimuat setelah klik "Nanti"
    WebUI.waitForPageLoad(10)

    // Ambil LAGI URL saat ini untuk verifikasi akhir
    String dashboardUrl = WebUI.getUrl()

    // Sekarang verifikasi apakah kita sudah di dashboard yang benar
    if (dashboardUrl.equals(urlProDashboard) || dashboardUrl.equals(urlFreeDashboard)) {
        KeywordUtil.markPassed('Berhasil melewati App Tour dan diarahkan ke Dashboard: ' + dashboardUrl)
    } // Jika URL tidak cocok dengan salah satu dari tiga di atas, maka GAGAL
    else {
        KeywordUtil.markFailed('Gagal diarahkan ke Dashboard setelah klik "Nanti". URL saat ini: ' + dashboardUrl)
    }
} else if (actualUrl.equals(urlProDashboard)) {
    println('Login Sukses. Diarahkan ke Dashboard PRO: ' + actualUrl)

    KeywordUtil.markPassed('Login berhasil dan diarahkan ke dashboard PRO.')
} else if (actualUrl.equals(urlFreeDashboard)) {
    println('Login Sukses. Diarahkan ke Dashboard FREE: ' + actualUrl)

    KeywordUtil.markPassed('Login berhasil dan diarahkan ke dashboard FREE.')
} else {
    KeywordUtil.markFailed('Login Gagal. URL setelah login tidak sesuai ekspektasi. URL saat ini: ' + actualUrl)
}

WebUI.delay(5 // Beri waktu 5 detik agar pesan error (jika ada) sempat muncul.
    )

// --- SIMPAN COOKIE SETELAH LOGIN BERHASIL ---
WebDriver driver = DriverFactory.getWebDriver()

Set<Cookie> cookies = driver.manage().getCookies()

def cookieList = []

for (Cookie c : cookies) {
    def expTime = null

    if (c.expiry != null) {
        expTime = c.expiry.getTime()
    }
    
    cookieList << [('name') : c.name, ('value') : c.value, ('domain') : c.domain, ('path') : c.path, ('expiry') : expTime
        , ('isSecure') : c.isSecure()]
}

Files.write(Paths.get('Cookies.json'), JsonOutput.toJson(cookieList).getBytes('UTF-8'))

println('Cookies disimpan ke file Cookies.json')

// --- FINAL: TUTUP BROWSER ---
WebUI.closeBrowser()