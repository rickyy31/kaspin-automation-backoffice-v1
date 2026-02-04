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
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import java.util.Random as Random

def currentUrl = WebUI.getUrl(FailureHandling.OPTIONAL)

// Jika browser belum buka ATAU browser terbuka tapi balik ke halaman landing/login
if ((currentUrl == null) || currentUrl.contains('login')) {
    WebUI.callTestCase(findTestCase('Test Cases/Login/Login Staff/LG_S_001'), [:], FailureHandling.STOP_ON_FAILURE)

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

// Verifikasi produk paling atas
WebElement row = WebUI.findWebElement(findTestObject('Akses Halaman Barang atau Jasa Repo/Page_Kasir Pintar/Barang onTop'), 
    10)

List<WebElement> cols = row.findElements(By.tagName('td'))

String namaBarang = (cols[3]).getText().trim()
String hargaJual = (cols[5]).getText().trim()
String hargaDasar = (cols[6]).getText().trim()
String tipeBarang = (cols[9]).getText().trim()

WebUI.verifyNotEqual(namaBarang, '')
WebUI.verifyMatch(hargaJual, '.*[0-9].*', true)
WebUI.verifyMatch(hargaDasar, '.*[0-9].*', true)
WebUI.verifyNotEqual(tipeBarang, '')

println('Nama Barang  : ' + namaBarang)
KeywordUtil.logInfo('Nama Barang  : ' + namaBarang)
println('Harga Jual   : ' + hargaJual)
KeywordUtil.logInfo('Harga Jual   : ' + hargaJual)
println('Harga Dasar  : ' + hargaDasar)
KeywordUtil.logInfo('Harga Dasar  : ' + hargaDasar)
println('Tipe Barang  : ' + tipeBarang)
KeywordUtil.logInfo('Tipe Barang  : ' + tipeBarang)

// Klik button detail produk paling atas
WebElement btnDetail = cols.get(11).findElement(By.tagName('button'))
btnDetail.click()

WebUI.click(findTestObject('Akses Halaman Barang atau Jasa Repo/Page_Kasir Pintar/Button Hapus Barang'))

TestObject btnConfirm = findTestObject('Akses Halaman Barang atau Jasa Repo/Page_Kasir Pintar/Button Konfirmasi Hapus')

WebUI.waitForElementVisible(btnConfirm, 10)
WebUI.click(btnConfirm)

// Verif barang yang sudah terhapus dari list barang
WebUI.delay(2) // kasih waktu reload

List<WebElement> allRows = WebUI.findWebElements(
	findTestObject('Akses Halaman Barang atau Jasa Repo/Page_Kasir Pintar/Table List Barang'),
	10
)

boolean isFound = false

for (WebElement r : allRows) {
	if (r.getText().contains(namaBarang)) {
		isFound = true
		break
	}
}

// Logging dulu sebelum verify
if (isFound) {
	println("Barang masih ditemukan setelah dihapus: " + namaBarang)
	KeywordUtil.logInfo("Barang masih ditemukan setelah dihapus: " + namaBarang)
} else {
	println("Barang berhasil dihapus, tidak ditemukan di tabel: " + namaBarang)
	KeywordUtil.logInfo("Barang berhasil dihapus, tidak ditemukan di tabel: " + namaBarang)
}

// Assertion final
WebUI.verifyEqual(isFound, false)