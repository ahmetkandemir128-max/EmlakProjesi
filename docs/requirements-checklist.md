# BT-KL-150 Gereksinim Kontrolu

PDF'te okunabilen ana beklentilere gore proje durumu:

| Gereksinim | Durum | Karsilayan Bolum |
| --- | --- | --- |
| Java tabanli web uygulamasi | Karsilandi | Spring Boot MVC |
| Spring Boot/EJB benzeri kurumsal yapi | Karsilandi | Controller, Service, Repository, Domain katmanlari |
| ORM kullanimi | Karsilandi | Spring Data JPA / Hibernate |
| Iliskisel veritabani | Karsilandi | H2 gelistirme DB, PostgreSQL surucusu ve ayar notlari |
| HTML5, CSS, JavaScript ekranlari | Karsilandi | Thymeleaf template, `app.css`, `app.js` |
| Kisi kaydi | Karsilandi | `/people` |
| Alan, satan, kiraci, kiraya veren rolleri | Karsilandi | `PersonRole` |
| Emlak kaydi | Karsilandi | `/properties/new` |
| Emlak arama | Karsilandi | `/` |
| Satis/kiralama senaryosu | Karsilandi | `/deals` ile emlak ve alici/kiraci eslestirme |
| Islem sonrasi emlak durum guncelleme | Karsilandi | Satis sonrasi `SOLD`, kiralama sonrasi `RENTED` |
| Raporlama, istatistik, grafik | Karsilandi | `/reports` |
| Form dogrulama | Karsilandi | Bean Validation ve form hata mesajlari |

Not: PDF'te ekran goruntulerinin JPEG/PNG olarak teslim edilmesi isteniyorsa, uygulama calistirildiktan sonra ekran goruntuleri ayrica alinmalidir. Kaynak kod tarafinda bu teslim kalemi icin gerekli ekranlar hazirdir.
