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

WebUI.openBrowser('https://laravel-qa.kpntr.com')

WebUI.maximizeWindow()

WebUI.click(findTestObject('Login Repo/LG_001 - Login valid as owner/Page_Kasir Pintar  Aplikasi Kasir Digital untuk UMKM Indonesia/a_Daftar_btn_login_page'))

WebUI.waitForPageLoad(20)

WebUI.verifyElementText(findTestObject('Login Repo/LG_001 - Login valid as owner/Page_Kasir Pintar  Aplikasi Kasir Digital untuk UMKM Indonesia/p_Inggris_font-32-sb font-black'), 
    'Login Owner')

WebUI.selectOptionByValue(findTestObject('Login Repo/LG_002 - Login valid as staff/Page_Kasir Pintar  Aplikasi Kasir Digital untuk UMKM Indonesia/select_Daftar di sini_sebagai'), 
    'staff', true)

WebUI.verifyOptionSelectedByValue(findTestObject('Login Repo/LG_001 - Login valid as owner/Page_Kasir Pintar  Aplikasi Kasir Digital untuk UMKM Indonesia/select_Daftar di sini_sebagai'), 
    'staff', true, 0)

WebUI.setText(findTestObject('Login Repo/LG_002 - Login valid as staff/Page_Kasir Pintar  Aplikasi Kasir Digital untuk UMKM Indonesia/input_E-mail_Staff'), 
    'rizkymanual2staff1@yopmail.com')

WebUI.setEncryptedText(findTestObject('Login Repo/LG_002 - Login valid as staff/Page_Kasir Pintar  Aplikasi Kasir Digital untuk UMKM Indonesia/input_Password_Staff'), 
    'UEVYwiROBa40zEp3J3f0UQ==')

WebUI.click(findTestObject('Login Repo/LG_002 - Login valid as staff/Page_Kasir Pintar  Aplikasi Kasir Digital untuk UMKM Indonesia/button_Password_submit'))

WebUI.waitForPageLoad(20)

WebUI.delay(5)

WebUI.verifyElementText(findTestObject('Login Repo/LG_002 - Login valid as staff/Page_Kasir Pintar/p_Baru_upper-text'), 
    'DASHBOARD')

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

