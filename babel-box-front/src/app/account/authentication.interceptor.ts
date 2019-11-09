import { Injectable } from '@angular/core';

import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';

const INVALID_TOKEN_CAUSE = 'Missing/Invalid token.';

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {

  constructor(private router: Router) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req)
      .pipe(
        catchError((err) => {
          console.log(err);
          if (err.error.message === INVALID_TOKEN_CAUSE) {
            this.router.navigateByUrl('/login');
          }
          return of(err);
        })
      );
  }
}
