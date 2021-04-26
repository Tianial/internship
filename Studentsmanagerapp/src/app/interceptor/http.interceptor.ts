import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http/http.d";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})

export class HttpClientInterceptor implements HttpInterceptor{

  public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    req = req.clone({
      setHeaders: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Credentials': 'true',
        'Access-Control-Allow-Origin': '*',
				'X-Requested-With': 'XMLHttpRequest'
      }
    });

    return next.handle(req);
  }

}
