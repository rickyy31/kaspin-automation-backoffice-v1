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

// 1. Buka Browser dan navigasi ke URL target
WebUI.openBrowser('')

WebUI.navigateToUrl('https://laravel-qa.kpntr.com')

WebUI.maximizeWindow( // Sebaiknya maximize window untuk memastikan konsistensi
    )

// 2. Definisikan Test Object yang dibutuhkan secara dinamis di dalam script
// Test Object untuk tombol 'MENU' (Burger Menu) di tampilan mobile
TestObject burgerMenu = new TestObject('burgerMenu')

burgerMenu.addProperty('xpath', ConditionType.EQUALS, '//*[@id="navbar-main"]/div/button')

// Test Object untuk tombol 'Masuk'. Asumsinya path ini sudah benar.
TestObject tombolMasuk = findTestObject('Landing Page_Kasir Pintar/a_Daftar_btn_login_page')

// 3. Logika untuk mengecek tampilan Mobile atau Desktop
// Cek apakah tombol burgerMenu terlihat.
// FailureHandling.OPTIONAL berarti test tidak akan gagal jika elemen tidak ditemukan.
if (WebUI.verifyElementVisible(burgerMenu, FailureHandling.OPTIONAL)) {
    // ---- KONDISI MOBILE ----
    println('Tampilan Mobile terdeteksi.')

    // Klik tombol burger menu untuk memunculkan menu dropdown
    WebUI.click(burgerMenu)

    // Tunggu hingga tombol 'Masuk' bisa di-klik
    WebUI.waitForElementClickable(tombolMasuk, 10)

    // Klik tombol 'Masuk'
    WebUI.click(tombolMasuk // ---- KONDISI DESKTOP ----
        ) // Langsung klik tombol 'Masuk' karena sudah terlihat
} else {
    println('Tampilan Desktop terdeteksi.')

    WebUI.click(tombolMasuk)
}

// 4. Verifikasi bahwa navigasi ke halaman login berhasil
// Pastikan URL yang diharapkan sudah benar. Seringkali URL login ada di subdomain berbeda.
// Contoh: 'https://app.development.kpntr.com/login'. Sesuaikan jika perlu.
WebUI.verifyEqual(WebUI.getUrl(), 'https://adonis.kpntr.com/login')

//WebUI.verifyUrl('https://adonis.kpntr.com/login')
println('Berhasil masuk ke halaman login.')

// 5. Tutup Browser
WebUI.closeBrowser()

