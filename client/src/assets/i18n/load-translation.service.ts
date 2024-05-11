import {Injectable} from "@angular/core";
import {ToastrService} from "ngx-toastr";
import {TranslateService} from "@ngx-translate/core";

@Injectable({
  providedIn: "root"
})
export class LoadTranslationService {

  constructor(private toast: ToastrService, private translate: TranslateService) {
  }

  switchLanguage(lang: string, screen: string) {
    this.translate.use(lang + '/' + screen);
    this.toast.info("Thay đổi ngôn ngữ thành công: " + lang)
  }

  langStorage() {
    return localStorage.getItem('lang') ? localStorage.getItem('lang') + '/' : 'vi/';
  }

}
