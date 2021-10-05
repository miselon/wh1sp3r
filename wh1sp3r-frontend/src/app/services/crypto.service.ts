import { Injectable } from '@angular/core';
import * as CryptoJS from 'crypto-js';  

@Injectable({
  providedIn: 'root'
})
export class CryptoService {

  private encoding = CryptoJS.enc.Utf8
  private keyChars = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'

  constructor() { }

  public encrypt(text: string, key: string): string{
    return CryptoJS.AES.encrypt(text, key).toString()
  }

  public decrypt(cipher: string, key: string): string{
    console.log('decrypting [' + cipher + ']')
    return CryptoJS.AES.decrypt(cipher, key).toString(this.encoding)
  }

  public generateKey(length: number): string{
    var result = '';
    for (var i = length; i > 0; --i) result += this.keyChars[Math.floor(Math.random() * this.keyChars.length)];
    return result;
  }

}
