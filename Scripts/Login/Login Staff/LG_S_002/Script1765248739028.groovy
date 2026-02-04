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

def currentUrl = WebUI.getUrl(FailureHandling.OPTIONAL)

// Jika browser belum buka ATAU browser terbuka tapi balik ke halaman landing/login
if ((currentUrl == null) || currentUrl.contains('login')) {
    WebUI.callTestCase(findTestCase('Test Cases/Login/Login Staff/LG_S_001'), [:], FailureHandling.STOP_ON_FAILURE)

    WebUI.navigateToUrl('https://laravel-qa.kpntr.com/account/other' // Jika masih login, langsung pindah halaman
        )
} else {
    WebUI.navigateToUrl('https://laravel-qa.kpntr.com/account/other')
}

WebUI.click(findTestObject('Login Repo/LG_002 - Login valid as staff/Page_Kasir Pintar/user_dropdown'))

WebUI.click(findTestObject('Login Repo/LG_002 - Login valid as staff/Page_Kasir Pintar/button_logout'))

// --- FINAL: TUTUP BROWSER ---
//WebUI.closeBrowser()
