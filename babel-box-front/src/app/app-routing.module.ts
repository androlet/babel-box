import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './account/login/login.component';
import { SearchTranslationsComponent } from './search/search-translations/search-translations.component';
import { ExerciseTranslationsComponent } from './exercises/exercise-translations/exercise-translations.component';
import { FrontOfficeComponent } from './layouts/front-office/front-office.component';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'front-office',
    component: FrontOfficeComponent,
    children: [
      {
        path: 'search',
        component: SearchTranslationsComponent
      },
      {
        path: 'qcm',
        component: ExerciseTranslationsComponent
      },
    ]
  },
  { path: '**', redirectTo: '/front-office/search', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  providers: [Location, {provide: LocationStrategy, useClass: HashLocationStrategy}],
  exports: [RouterModule]
})
export class AppRoutingModule { }
