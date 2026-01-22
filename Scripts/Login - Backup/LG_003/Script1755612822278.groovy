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

// --- LANGKAH 1: BUKA BROWSER DAN AKSES HALAMAN UTAMA ---
WebUI.openBrowser('')

WebUI.navigateToUrl('https://adonis.kpntr.com/')

WebUI.maximizeWindow()

// --- LANGKAH 2: KLIK TOMBOL MASUK (HANDLE MOBILE & DESKTOP) ---
// Definisikan Test Object yang dibutuhkan
TestObject burgerMenu = new TestObject('burgerMenu')

burgerMenu.addProperty('xpath', ConditionType.EQUALS, '//*[@id="navbar-main"]/div/button')

TestObject tombolMasukHeader = findTestObject('null')

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
WebUI.verifyElementPresent(findTestObject('null'), 10)

println('Verifikasi "Masuk Sebagai Owner" berhasil.')

// 4. Masukkan E-Mail Owner yang valid.
WebUI.setText(findTestObject('null'), 'rizkymanual1@yopmail.com')

println('Email berhasil diinput.')

// 5. Masukkan Password Owner yang valid.
// Direkomendasikan menggunakan Encrypted Text untuk keamanan.
// Cara membuat: Klik Tools > Encrypt Text, masukkan 'manual123', klik Encrypt.
// Lalu copy hasilnya dan paste di sini. Contoh: 'jKLmN....='
WebUI.setText(findTestObject('null'), 'manual123' // Ganti dengan hasil enkripsi dari 'manual123'
    )

println('Password berhasil diinput.')

// 6. Klik tombol "Masuk".
WebUI.click(findTestObject('null'))

println('Tombol Masuk di form login diklik. Menunggu redirect...')

WebUI.delay(5) // Beri waktu 5 detik agar pesan error (jika ada) sempat muncul.

// --- EKSPEKTASI: VERIFIKASI HASIL LOGIN DENGAN 3 KEMUNGKINAN URL ---
WebUI.waitForPageLoad(20 // Beri waktu lebih lama untuk redirect setelah login
    )

// Definisikan 3 kemungkinan URL sukses
String urlAppTour = 'https://adonis.kpntr.com/account/app_tour'

String urlFreeDashboard = 'https://adonis.kpntr.com/account/monitoring_data/show_dapat/show_untung'

String urlProDashboard = 'https://adonis.kpntr.com/account'

// Dapatkan URL saat ini setelah login
String actualUrl = WebUI.getUrl()

// Lakukan pengecekan
if (actualUrl.equals(urlProDashboard)) {
    println('Login Sukses. Diarahkan ke Dashboard PRO: ' + actualUrl)

    KeywordUtil.markPassed('Login berhasil dan diarahkan ke dashboard PRO.' // Jika URL tidak cocok dengan salah satu dari tiga di atas, maka GAGAL
        )
} else if (actualUrl.equals(urlFreeDashboard)) {
    println('Login Sukses. Diarahkan ke Dashboard FREE: ' + actualUrl)

    KeywordUtil.markPassed('Login berhasil dan diarahkan ke dashboard FREE.')
} else if (actualUrl.equals(urlAppTour)) {
    println('Login Sukses. Diarahkan ke Halaman App Tour (Akun Baru): ' + actualUrl)

    KeywordUtil.markPassed('Login berhasil dan diarahkan ke halaman App Tour.')
} else {
    KeywordUtil.markFailed('Login Gagal. URL setelah login tidak sesuai ekspektasi. URL saat ini: ' + actualUrl)
}

WebUI.delay(5) // Beri waktu 5 detik agar pesan error (jika ada) sempat muncul.

// --- FINAL: TUTUP BROWSER ---
WebUI.closeBrowser()