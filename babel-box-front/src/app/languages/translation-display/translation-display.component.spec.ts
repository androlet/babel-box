import {TranslationResults} from '../../search/domain/translation-results';
/* tslint:disable:no-unused-variable */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LanguagesModuleTesting, TranslationDisplayComponentTesting } from '../languages.module.testing';
import { TranslationDisplayComponent } from './translation-display.component';


describe('TranslationDisplayComponent', () => {
  let component: TranslationDisplayComponent;
  let fixture: ComponentFixture<TranslationDisplayComponent>;

  const translation: TranslationResults = {
    from: 'en',
    to: 'fr',
    originalTerm: 'test',
    pronunciation: 'ˈtɛst',
    results: [
      {
        signification: 'interro',
        originalExample:
          'I have a German test today; I hope I get good results.',
        translatedExample:
          'J\'ai une interro d\'allemand aujourd\'hui. J\'espère avoir une bonne note.'
      },
      {
        signification: 'mettre',
        originalExample: 'The long wait tested Jessica\'s patience.',
        translatedExample:
          'La longue attente a mis la patience de Jessica à rude épreuve.'
      }
    ]
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [LanguagesModuleTesting]
    }).compileComponents();

    fixture = TestBed.createComponent(TranslationDisplayComponentTesting);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
