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
import com.kms.katalon.core.util.KeywordUtil
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import java.text.SimpleDateFormat
import java.util.Locale

// =====================================================
// PRECONDITION — LOAD SESSION LOGIN DARI COOKIE
// =====================================================

WebUI.callTestCase(
	findTestCase('Login/Login Owner/LG_004'),
	[:]
)

// =====================================================
// (1) VERIFIKASI ADA BADGE "1 Hari" DI MENU PEMBAYARAN
// =====================================================

TestObject badgePembayaran = findTestObject('Pembayaran/badge_reminder')

if (WebUI.verifyElementPresent(badgePembayaran, 10, FailureHandling.OPTIONAL)) {

    String badgeText = WebUI.getText(badgePembayaran)
    KeywordUtil.markPassed("Badge ditemukan dengan teks: " + badgeText)

} else {

    KeywordUtil.markFailed("Badge '1 Hari' TIDAK ditemukan di menu pembayaran")

}

// =====================================================
// (2) VERIFIKASI ADA NOTIF REMINDER BERLANGGANAN
// =====================================================

TestObject bannerReminder = findTestObject('Pembayaran/banner_reminder')

if (WebUI.verifyElementPresent(bannerReminder, 10, FailureHandling.OPTIONAL)) {

    if (WebUI.verifyTextPresent("Langganan Kasir Pintar Pro Akan Segera Habis", false, FailureHandling.OPTIONAL)) {
        KeywordUtil.markPassed("Banner reminder langganan tampil dengan teks yang benar")
    } else {
        KeywordUtil.markFailed("Banner reminder muncul, tapi teks TIDAK sesuai")
    }

} else {

    KeywordUtil.markFailed("Banner reminder langganan TIDAK tampil")

}

// --- FINAL: TUTUP BROWSER ---
WebUI.closeBrowser()
