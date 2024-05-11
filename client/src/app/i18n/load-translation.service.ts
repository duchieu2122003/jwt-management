import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {catchError, Observable, of} from "rxjs";
import {ToastrService} from "ngx-toastr";
import {TranslateService} from "@ngx-translate/core";


@Injectable({
  providedIn: "root"
})
export class LoadTranslationService {

  private locale: { [key: string]: any } = {};
  private messages: string;

  constructor(private http: HttpClient, private toast: ToastrService, private translate: TranslateService) {
    this.messages = '';
  }

  // initAllLanguage(): Observable<any> {
  //   return new Observable<any>(response => {
  //     this.http.get(`src/app/i18n/${this.lang}.json`).subscribe(
  //       data => {
  //         this.locale[this.lang] = data;
  //         response.next(true);
  //         response.complete();
  //       }, error => {
  //         this.toast.error("Lỗi Init Language");
  //         console.log(error);
  //         response.error(error);
  //       }
  //     )
  //   })
  // }
  // getMessage(key: string): string {
  //   if (this.locale[this.lang] && this.locale[this.lang][key]) {
  //     return this.locale[this.lang][key];
  //   }
  //   return '';
  // }
  loadTranslations(screen: string): void {
    const languages = ['en', 'vi'];
    languages.forEach(lang => {
      this.http.get(`./app/i18n/${screen}.${lang}.json`).subscribe(
        translations => {
          this.translate.setTranslation(lang, translations);
        },
        error => {
          console.error(`Error loading translations for ${lang}.`);
          console.log(error);
        }
      );
    });
  }
  getData(screen: string, lang: string): Observable<any> {
    return this.http.get<any>(`app/i18n/${screen}/${screen}.${lang}.json`);
  }
  switchLanguage(lang: string,screen:string) {
    this.translate.use(lang+'/'+screen);
    this.toast.info("Thay đổi ngôn ngữ thành công: " + lang)
  }

  // loadTranslations(screen:string): void {
  //   const languages = ['en', 'vi'];
  //   languages.forEach(lang => {
  //     // Lấy file ngôn ngữ từ thư mục của từng entity
  //     this.http.get(`./i18n/${screen}.${lang}.json`).subscribe(
  //       translations => {
  //         // Đặt các bản dịch cho ngôn ngữ tương ứng
  //         this.translate.setTranslation(lang, translations);
  //       },
  //       error => {
  //         console.error(`Error loading translations for ${lang}.`);
  //         console.log(error)
  //       }
  //     );
  //   });
  // }

}
