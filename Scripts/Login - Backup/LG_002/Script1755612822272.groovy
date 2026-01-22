import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

/*
 * Test Case: Akses Login via URL Langsung
 * Deskripsi: Test case ini memastikan halaman login dapat diakses
 *            langsung dengan menavigasi ke URL spesifik.
 */

// 1. Buka Browser
// Parameter string kosong '' akan menggunakan browser default yang diatur di project setting.
WebUI.openBrowser('')
println('Browser berhasil dibuka.')

// 2. Navigasi Langsung ke URL Halaman Login
// Ini adalah langkah inti dari test case ini.
def loginUrl = 'https://adonis.kpntr.com/login'
WebUI.navigateToUrl(loginUrl)
println("Berhasil menavigasi ke URL: " + loginUrl)

WebUI.maximizeWindow( // Sebaiknya maximize window untuk memastikan konsistensi
)

// 3. Verifikasi (Penting!)
// Kita perlu memastikan bahwa kita benar-benar berada di halaman yang benar.
// Cara pertama: Verifikasi URL-nya.
//WebUI.verifyUrl(loginUrl)
WebUI.verifyEqual(WebUI.getUrl(), loginUrl)
println('Verifikasi URL berhasil. Saat ini berada di halaman login.')

// (Opsional tapi sangat direkomendasikan)
// Cara kedua yang lebih baik: Verifikasi keberadaan elemen kunci di halaman login,
// misalnya field untuk memasukkan email/username. Ini lebih kuat daripada hanya cek URL.
// Anda perlu membuat Test Object untuk field email terlebih dahulu.
// Contoh jika objectnya bernama 'Page_Login/input_email':
// WebUI.verifyElementPresent(findTestObject('Page_Login/input_email'), 10)
// println('Verifikasi elemen field email berhasil. Halaman login telah dimuat dengan benar.')

// 4. Tutup Browser
// Langkah ini penting untuk mengakhiri sesi tes dengan bersih.
WebUI.closeBrowser()
println('Test case selesai dan browser ditutup.')