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

//WebUI.openBrowser('')
WebUI.newTab('https://laravel-qa.kpntr.com/account')
//WebUI.navigateToUrl('https://laravel-qa.kpntr.com/account')

//WebUI.maximizeWindow()
//
//WebUI.verifyElementText(findTestObject('Pro - Report Sales Repo/SDT_001 - Download Sales Data Transaction/Page_Kasir Pintar Login/p_Inggris_font-32-sb font-black'), 
//    'Login Owner')
//
//WebUI.selectOptionByValue(findTestObject('Pro - Report Sales Repo/SDT_001 - Download Sales Data Transaction/Page_Kasir Pintar Login/select_Daftar di sini_sebagai'), 
//    'owner', true, FailureHandling.STOP_ON_FAILURE)
//
//WebUI.setText(findTestObject('Pro - Report Sales Repo/SDT_001 - Download Sales Data Transaction/Page_Kasir Pintar Login/input_Daftar di sini_email'), 
//    'rizkymanual1@yopmail.com')
//
//WebUI.setEncryptedText(findTestObject('Pro - Report Sales Repo/SDT_001 - Download Sales Data Transaction/Page_Kasir Pintar Login/input_Daftar di sini_password'), 
//    'UEVYwiROBa40zEp3J3f0UQ==')
//
//WebUI.click(findTestObject('Pro - Report Sales Repo/SDT_001 - Download Sales Data Transaction/Page_Kasir Pintar Login/button_Daftar di sini'))
//
//WebUI.waitForPageLoad(20)
//
//WebUI.delay(5)
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

WebUI.selectOptionByValue(findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Form Input Tipe Barang'), 'default', 
    true)

WebUI.uploadFile(findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Form Input Upload Foto Barang'), 'C:\\Users\\MODERN\\Downloads\\Sample file for testing\\png\\file_example_PNG_3MB - Copy - Copy.png')

WebUI.setText(findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Form Input Nama Barang'), 'Barang Default 01')

WebUI.click(findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Button Kode Barang'))

WebUI.setText(findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Form Input Harga Beli'), '15000')

WebUI.setText(findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Form Input Harga Jual'), '20000')

WebUI.selectOptionByValue(findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Form Input Jenis Stok'), '0', 
    true)

WebUI.setText(findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Form Input Stok'), '100')

WebUI.click(findTestObject('Halaman Form Tambah Barang Repo/Page_Kasir Pintar/Button Simpan Tambah Barang'))

WebUI.waitForPageLoad(20)

WebUI.verifyElementText(findTestObject('Akses Halaman Barang atau Jasa Repo/Page_Kasir Pintar/Judul Halaman Barang atau Jasa'), 
    'Barang atau Jasa')

WebUI.closeBrowser()

