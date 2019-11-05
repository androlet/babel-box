import {OnDestroy} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {takeUntil} from 'rxjs/operators';

export class BaseComponent implements OnDestroy {

  private subscriptionsCloser = new Subject();

  public safelySubscriptionable(observable: Observable<any>) {
   return observable.pipe(takeUntil(this.subscriptionsCloser));
  }

  ngOnDestroy(): void {
    this.subscriptionsCloser.next();
    this.subscriptionsCloser.complete();
  }

}
