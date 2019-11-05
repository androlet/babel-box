import { Component, OnInit } from '@angular/core';
import {LanguageOption} from '../domain/language-option';
import {LanguagesService} from '../services/languages.service';
import {BaseComponent} from '../../base.component';

@Component({
  selector: 'app-languages-selector',
  templateUrl: './languages-selector.component.html',
  styleUrls: ['./languages-selector.component.less']
})
export class LanguagesSelectorComponent extends BaseComponent implements OnInit {

  original: LanguageOption;
  target: LanguageOption;
  languagesList: LanguageOption[] = [];

  constructor(private languagesService: LanguagesService) {
    super();
  }

  ngOnInit() {
    this.loadLanguages();
  }

  hasLanguages(): boolean {
    return this.languagesList.length > 0;
  }

  isSelectedAsOriginal(option: LanguageOption): boolean {
    return this.original.id === option.id;
  }

  isSelectedAsTarget(option: LanguageOption): boolean {
    return this.target.id === option.id;
  }

  reverseLanguageSelected(): void {
    const oldOl = this.original;
    this.original = this.target;
    this.target = oldOl;
  }

  getOriginalLanguageOptions(): LanguageOption[] {
      return this.languagesList.filter(lo => lo.id !== this.target.id);
  }

  getTargetLanguageOptions(): LanguageOption[] {
      return this.languagesList.filter(lo => lo.id !== this.original.id);
  }

  private loadLanguages(): void {
    this.safelySubscriptionable(this.languagesService.getLanguageOptions())
      .subscribe(languageOptions => {
        this.languagesList = languageOptions;
        this.selectDefaultLanguages();
      });
  }

  private selectDefaultLanguages(): void {
    this.original = this.languagesList[0];
    this.target = this.languagesList[1];
  }

}
