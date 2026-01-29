import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
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
import java.util.Random as Random
// imports khusus cookie
import org.openqa.selenium.Cookie as Cookie
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import java.util.Date as Date
import groovy.json.JsonSlurper as JsonSlurper
import java.nio.file.Files as Files
import java.nio.file.Paths as Paths

def currentUrl = WebUI.getUrl(FailureHandling.OPTIONAL)

// Jika browser belum buka ATAU browser terbuka tapi balik ke halaman landing/login
if ((currentUrl == null) || currentUrl.contains('login')) {
    WebUI.callTestCase(findTestCase('Test Cases/Login/Login Staff/LG_003_Staff'), [:], FailureHandling.STOP_ON_FAILURE)

    WebUI.navigateToUrl('https://laravel-qa.kpntr.com/account/other' // Jika masih login, langsung pindah halaman
        )
} else {
    WebUI.navigateToUrl('https://laravel-qa.kpntr.com/account/other')
}

WebUI.verifyElementText(findTestObject('Akses Halaman Barang atau Jasa Repo/Page_Kasir Pintar/Menu Title Database'), 'DATABASE')

WebUI.verifyElementClickable(findTestObject('Akses Halaman Barang atau Jasa Repo/Page_Kasir Pintar/Menu Database'))

WebUI.click(findTestObject('Akses Halaman Barang atau Jasa Repo/Page_Kasir Pintar/Menu Database'))

WebUI.verifyElementClickable(findTestObject('Akses Halaman Barang atau Jasa Repo/Page_Kasir Pintar/Menu Barang atau Jasa'))

WebUI.click(findTestObject('Akses Halaman Barang atau Jasa Repo/Page_Kasir Pintar/Menu Barang atau Jasa'))

WebUI.waitForPageLoad(20)

WebUI.verifyElementText(findTestObject('Akses Halaman Barang atau Jasa Repo/Page_Kasir Pintar/Judul Halaman Barang atau Jasa'), 
    'Barang atau Jasa')

WebUI.verifyElementClickable(findTestObject('Akses Halaman Barang atau Jasa Repo/Page_Kasir Pintar/Button Tambah Produk'))

WebUI.click(findTestObject('Akses Halaman Barang atau Jasa Repo/Page_Kasir Pintar/Button Tambah Produk'))

WebUI.waitForPageLoad(20)

WebUI.verifyElementText(findTestObject('Akses Halaman Barang atau Jasa Repo/Page_Kasir Pintar/Judul Halaman Tambah Produk'), 
    'Tambah Produk')

WebUI.selectOptionByValue(findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Form Input Tipe Barang'), 'add_on', 
    false)

//WebUI.click(findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Form Input Upload Foto Barang'))
//
//WebUI.waitForElementVisible(findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Popup Upload Gambar'), 
//    5)
//
//WebUI.verifyTextPresent('Upload Gambar', false)
//
//WebUI.uploadFile(findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Choose File Gambar'), 'E:\\JOB\\KasPin\\SS\\gambar testing1.png')
//
//// klik Oke
////WebUI.click(findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Button Oke Upload Gambar'))
//// scroll ke button Oke
////WebUI.executeJavaScript('\n    let btn = document.getElementById(\'Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Button Oke Upload Gambar\');\n    if (btn) {\n        btn.scrollIntoView({block: \'center\'});\n    }\n', 
////    null)
//
//TestObject btnOke = findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar//Button Oke Upload Gambar')
//
//WebUI.scrollToElement(btnOke, 5)
//WebUI.waitForElementClickable(btnOke, 10)
//WebUI.click(btnOke)
//
//def randomNum = new Random().nextInt(9000) + 1000 // 4 digit
//
//WebUI.setText(findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Form Input Nama Barang'), "Barang Default $randomNum")
//
//WebUI.click(findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Button Kode Barang'))
//
//WebUI.setText(findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Form Input Harga Beli'), '15000')
//
//WebUI.setText(findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Form Input Harga Jual'), '20000')
//
//WebUI.selectOptionByValue(findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Form Input Jenis Stok'), '0', 
//    true)
//
//WebUI.setText(findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Form Input Stok'), '100')
//
//// scroll ke button Simpan
//WebUI.executeJavaScript('\n    let btn = document.getElementById(\'Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Button Simpan Tambah Barang\');\n    if (btn) {\n        btn.scrollIntoView({block: \'center\'});\n    }\n', 
//    null)
//
//WebUI.delay(1)
//
//// klik Simpan
//TestObject btn = findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Button Simpan Tambah Barang')
//
//WebUI.executeJavaScript('\n    arguments[0].scrollIntoView({block: \'center\'});\n', Arrays.asList(WebUI.findWebElement(
//            btn, 10)))
//
//WebUI.executeJavaScript('arguments[0].click();', Arrays.asList(WebUI.findWebElement(btn, 10)))
//
//WebUI.waitForPageLoad(20)
//
//WebUI.verifyElementText(findTestObject('Akses Halaman Barang atau Jasa Repo/Page_Kasir Pintar/Judul Halaman Barang atau Jasa'), 
//    'Barang atau Jasa')
//
////Kurang klik balik ke barang dan jasa kemudian verifikasi