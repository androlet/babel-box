import { NgModule, Component } from '@angular/core';
import { FrontOfficeComponent } from './front-office/front-office.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { LayoutsModule } from './layouts.module';
import { AccountModule } from '../account/account.module';

@Component({
  selector: 'app-nav-bar',
  template: ''
})
export class NavBarComponentTested extends NavBarComponent {}

@Component({
  selector: 'app-front-office',
  template: ''
})
export class FrontOfficeComponentTested extends FrontOfficeComponent {}

@NgModule({
  declarations: [FrontOfficeComponentTested, NavBarComponentTested],
  imports: [
    AccountModule,
    LayoutsModule
  ],
  exports: [FrontOfficeComponentTested, NavBarComponentTested]
})
export class LayoutsModuleTesting { }
