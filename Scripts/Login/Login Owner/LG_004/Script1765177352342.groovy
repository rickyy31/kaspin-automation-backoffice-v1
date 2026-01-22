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
// imports khusus cookie
import org.openqa.selenium.Cookie as Cookie
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import java.util.Date as Date
import groovy.json.JsonSlurper as JsonSlurper
import java.nio.file.Files as Files
import java.nio.file.Paths as Paths

// ----------------- mulai eksekusi -----------------
WebUI.openBrowser('')

WebUI.navigateToUrl('https://laravel-qa.kpntr.com/account' // harus akses domain dulu
    )

WebUI.maximizeWindow()

def file = new File('Cookies.json')

if (!(file.exists())) {
    println('File Cookies.json tidak ditemukan. Harap login dulu (jalankan TC login).')

    WebUI.closeBrowser()

    assert false
}

def cookieList = new JsonSlurper().parseText(file.text)

if (!(cookieList)) {
    println('Cookies.json kosong atau tidak valid.')

    WebUI.closeBrowser()

    assert false
}

WebDriver driver = DriverFactory.getWebDriver()

// inject cookie dengan lebih aman: buat builder dan panggil expiresOn hanya ketika ada expiry
for (def c : cookieList) {
    try {
        // buat builder
        Cookie.Builder builder = new Cookie.Builder(((c.name) as String), ((c.value) as String)).domain(((c.domain) as String)).path(
                ((c.path) as String))

        // set secure jika atribut ada
        if (c.containsKey('isSecure')) {
            // beberapa format mungkin pakai boolean atau string "true"
            builder = builder.isSecure(((c.isSecure) as boolean))
        }
        
        // set httpOnly jika ada
        if (c.containsKey('isHttpOnly')) {
            builder = builder.isHttpOnly(((c.isHttpOnly) as boolean))
        }
        
        // set expiry hanya kalau ada
        if (c.expiry) {
            Date exp = new Date(((c.expiry) as Long))

            builder = builder.expiresOn(exp)
        }
        
        Cookie cookie = builder.build()

        driver.manage().addCookie(cookie)
    }
    catch (Exception e) {
        println("Gagal menambahkan cookie $c.name : " + e.getMessage()) // lanjutkan ke cookie berikutnya (tidak menghentikan seluruh test)
    } 
}

println('Cookies berhasil diinjeksi (sebisa mungkin).')

WebUI.navigateToUrl('https://laravel-qa.kpntr.com/account')

WebUI.waitForPageLoad(10)