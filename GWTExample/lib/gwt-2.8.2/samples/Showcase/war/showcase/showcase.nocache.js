function showcase(){var U='bootstrap',V='begin',W='gwt.codesvr.showcase=',X='gwt.codesvr=',Y='showcase',Z='startup',$='DUMMY',_=0,ab=1,bb='iframe',cb='position:absolute; width:0; height:0; border:none; left: -1000px;',db=' top: -1000px;',eb='CSS1Compat',fb='<!doctype html>',gb='',hb='<html><head><\/head><body><\/body><\/html>',ib='undefined',jb='readystatechange',kb=10,lb='Chrome',mb='eval("',nb='");',ob='script',pb='javascript',qb='moduleStartup',rb='moduleRequested',sb='Failed to load ',tb='head',ub='meta',vb='name',wb='showcase::',xb='::',yb='gwt:property',zb='content',Ab='=',Bb='gwt:onPropertyErrorFn',Cb='Bad handler "',Db='" for "gwt:onPropertyErrorFn"',Eb='gwt:onLoadErrorFn',Fb='" for "gwt:onLoadErrorFn"',Gb='#',Hb='?',Ib='/',Jb='img',Kb='clear.cache.gif',Lb='baseUrl',Mb='showcase.nocache.js',Nb='base',Ob='//',Pb='locale',Qb='en',Rb='locale=',Sb=7,Tb='&',Ub='SHOWCASE_LOCALE=',Vb=';',Wb=16,Xb='_',Yb='__gwt_Locale',Zb='Unexpected exception in locale detection, using default: ',$b=2,_b=3,ac=4,bc='user.agent',cc='webkit',dc='safari',ec='msie',fc=11,gc='ie10',hc=9,ic='ie9',jc=8,kc='ie8',lc='gecko',mc='gecko1_8',nc='selectingPermutation',oc='showcase.devmode.js',pc='fr',qc='187073EF23DD8CF165B8CC822BC4CDD1',rc='default',sc='18B768E95DEAF68B7A1E19E01005FA20',tc='2589E83FB1A61308BF4B16DD29C4135F',uc='4FBE4127360AE082CD038F0020670B8D',vc='525E540442CF500833AA7530353CA7BC',wc='zh',xc='580B5113706EC780EA23271BE42EE871',yc='5ADE69D684BE59FA971A0D4F75EEF9B0',zc='5C1A880AE82C9901CF75D4BF6545847C',Ac='6B926C5B4DD0C9B85DD780AA4981BC01',Bc='80B53E131FB743EFBFFF9ADC3C9A6B43',Cc='84A419A88EFF2AE64AE1C3438E692272',Dc='ar',Ec='87FC47349AE61E397E589272B3DC5FBB',Fc='8B17EE0186F6951F9A9C0D029C778D37',Gc='9A5D1E3ECF287B1D042B3D129196F726',Hc='AE777EE8051AF7D649E6BFE734413804',Ic='B439B047D95925F75E7FAA042D454F7F',Jc='BB6F65E242886C4E252E020356769693',Kc='C166DC77B0BFB89E960A94B920049D1D',Lc='CF42E6245EB31AB0577F7AD93E3065EA',Mc='E1744EFC2B116D5BDD826F73F973A932',Nc='E8FFBA856F0335D5B6838531865051D8',Oc='EADD89A3AA0B832D074C70536D0A709D',Pc='F136F2BF5A178908F4D1DA08A340EA02',Qc='F6410A6D05AEDC9811F3D5E76CBA419F',Rc='F66D30FB1660E461C6BDBE25CCDBCE9F',Sc=':',Tc='.cache.js',Uc='loadExternalRefs',Vc='end',Wc='http:',Xc='file:',Yc='_gwt_dummy_',Zc='__gwtDevModeHook:showcase',$c='Ignoring non-whitelisted Dev Mode URL: ',_c=':moduleBase';var u=window;var v=document;A(U,V);function w(){var a=u.location.search;return a.indexOf(W)!=-1||a.indexOf(X)!=-1}
function A(a,b){if(u.__gwtStatsEvent){u.__gwtStatsEvent({moduleName:Y,sessionId:u.__gwtStatsSessionId,subSystem:Z,evtGroup:a,millis:(new Date).getTime(),type:b})}}
showcase.__sendStats=A;showcase.__moduleName=Y;showcase.__errFn=null;showcase.__moduleBase=$;showcase.__softPermutationId=_;showcase.__computePropValue=null;showcase.__getPropMap=null;showcase.__installRunAsyncCode=function(){};showcase.__gwtStartLoadingFragment=function(){return null};showcase.__gwt_isKnownPropertyValue=function(){return false};showcase.__gwt_getMetaProperty=function(){return null};var B=null;var C=u.__gwt_activeModules=u.__gwt_activeModules||{};C[Y]={moduleName:Y};showcase.__moduleStartupDone=function(e){var f=C[Y].bindings;C[Y].bindings=function(){var a=f?f():{};var b=e[showcase.__softPermutationId];for(var c=_;c<b.length;c++){var d=b[c];a[d[_]]=d[ab]}return a}};var D;function F(){G();return D}
function G(){if(D){return}var a=v.createElement(bb);a.id=Y;a.style.cssText=cb+db;a.tabIndex=-1;v.body.appendChild(a);D=a.contentWindow.document;D.open();var b=document.compatMode==eb?fb:gb;D.write(b+hb);D.close()}
function H(k){function l(a){function b(){if(typeof v.readyState==ib){return typeof v.body!=ib&&v.body!=null}return /loaded|complete/.test(v.readyState)}
var c=b();if(c){a();return}function d(){if(!c){if(!b()){return}c=true;a();if(v.removeEventListener){v.removeEventListener(jb,d,false)}if(e){clearInterval(e)}}}
if(v.addEventListener){v.addEventListener(jb,d,false)}var e=setInterval(function(){d()},kb)}
function m(c){function d(a,b){a.removeChild(b)}
var e=F();var f=e.body;var g;if(navigator.userAgent.indexOf(lb)>-1&&window.JSON){var h=e.createDocumentFragment();h.appendChild(e.createTextNode(mb));for(var i=_;i<c.length;i++){var j=window.JSON.stringify(c[i]);h.appendChild(e.createTextNode(j.substring(ab,j.length-ab)))}h.appendChild(e.createTextNode(nb));g=e.createElement(ob);g.language=pb;g.appendChild(h);f.appendChild(g);d(f,g)}else{for(var i=_;i<c.length;i++){g=e.createElement(ob);g.language=pb;g.text=c[i];f.appendChild(g);d(f,g)}}}
showcase.onScriptDownloaded=function(a){l(function(){m(a)})};A(qb,rb);var n=v.createElement(ob);n.src=k;if(showcase.__errFn){n.onerror=function(){showcase.__errFn(Y,new Error(sb+code))}}v.getElementsByTagName(tb)[_].appendChild(n)}
showcase.__startLoadingFragment=function(a){return K(a)};showcase.__installRunAsyncCode=function(a){var b=F();var c=b.body;var d=b.createElement(ob);d.language=pb;d.text=a;c.appendChild(d);c.removeChild(d)};function I(){var c={};var d;var e;var f=v.getElementsByTagName(ub);for(var g=_,h=f.length;g<h;++g){var i=f[g],j=i.getAttribute(vb),k;if(j){j=j.replace(wb,gb);if(j.indexOf(xb)>=_){continue}if(j==yb){k=i.getAttribute(zb);if(k){var l,m=k.indexOf(Ab);if(m>=_){j=k.substring(_,m);l=k.substring(m+ab)}else{j=k;l=gb}c[j]=l}}else if(j==Bb){k=i.getAttribute(zb);if(k){try{d=eval(k)}catch(a){alert(Cb+k+Db)}}}else if(j==Eb){k=i.getAttribute(zb);if(k){try{e=eval(k)}catch(a){alert(Cb+k+Fb)}}}}}__gwt_getMetaProperty=function(a){var b=c[a];return b==null?null:b};B=d;showcase.__errFn=e}
function J(){function e(a){var b=a.lastIndexOf(Gb);if(b==-1){b=a.length}var c=a.indexOf(Hb);if(c==-1){c=a.length}var d=a.lastIndexOf(Ib,Math.min(c,b));return d>=_?a.substring(_,d+ab):gb}
function f(a){if(a.match(/^\w+:\/\//)){}else{var b=v.createElement(Jb);b.src=a+Kb;a=e(b.src)}return a}
function g(){var a=__gwt_getMetaProperty(Lb);if(a!=null){return a}return gb}
function h(){var a=v.getElementsByTagName(ob);for(var b=_;b<a.length;++b){if(a[b].src.indexOf(Mb)!=-1){return e(a[b].src)}}return gb}
function i(){var a=v.getElementsByTagName(Nb);if(a.length>_){return a[a.length-ab].href}return gb}
function j(){var a=v.location;return a.href==a.protocol+Ob+a.host+a.pathname+a.search+a.hash}
var k=g();if(k==gb){k=h()}if(k==gb){k=i()}if(k==gb&&j()){k=e(v.location.href)}k=f(k);return k}
function K(a){if(a.match(/^\//)){return a}if(a.match(/^[a-zA-Z]+:\/\//)){return a}return showcase.__moduleBase+a}
function L(){var m=[];var n=_;function o(a,b){var c=m;for(var d=_,e=a.length-ab;d<e;++d){c=c[a[d]]||(c[a[d]]=[])}c[a[e]]=b}
var p=[];var q=[];function r(a){var b=q[a](),c=p[a];if(b in c){return b}var d=[];for(var e in c){d[c[e]]=e}if(B){B(a,d,b)}throw null}
q[Pb]=function(){var b=null;var c=Qb;try{if(!b){var d=location.search;var e=d.indexOf(Rb);if(e>=_){var f=d.substring(e+Sb);var g=d.indexOf(Tb,e);if(g<_){g=d.length}b=d.substring(e+Sb,g)}}if(!b){var h=v.cookie;var i=h.indexOf(Ub);if(i>=_){var g=h.indexOf(Vb,i);if(g<_){g=h.length}b=h.substring(i+Wb,g)}}if(!b){b=__gwt_getMetaProperty(Pb)}if(!b){var j=navigator.browserLanguage?navigator.browserLanguage:navigator.language;if(j){var k=j.split(/[-_]/);if(k.length>ab){k[ab]=k[ab].toUpperCase()}b=k.join(Xb)}}if(!b){b=u[Yb]}if(b){c=b}while(b&&!__gwt_isKnownPropertyValue(Pb,b)){var l=b.lastIndexOf(Xb);if(l<_){b=null;break}b=b.substring(_,l)}}catch(a){alert(Zb+a)}u[Yb]=c;return b||Qb};p[Pb]={'ar':_,'default':ab,'en':$b,'fr':_b,'zh':ac};q[bc]=function(){var a=navigator.userAgent.toLowerCase();var b=v.documentMode;if(function(){return a.indexOf(cc)!=-1}())return dc;if(function(){return a.indexOf(ec)!=-1&&(b>=kb&&b<fc)}())return gc;if(function(){return a.indexOf(ec)!=-1&&(b>=hc&&b<fc)}())return ic;if(function(){return a.indexOf(ec)!=-1&&(b>=jc&&b<fc)}())return kc;if(function(){return a.indexOf(lc)!=-1||b>=fc}())return mc;return gb};p[bc]={'gecko1_8':_,'ie10':ab,'ie8':$b,'ie9':_b,'safari':ac};__gwt_isKnownPropertyValue=function(a,b){return b in p[a]};showcase.__getPropMap=function(){var a={};for(var b in p){if(p.hasOwnProperty(b)){a[b]=r(b)}}return a};showcase.__computePropValue=r;u.__gwt_activeModules[Y].bindings=showcase.__getPropMap;A(U,nc);if(w()){return K(oc)}var s;try{o([pc,gc],qc);o([rc,dc],sc);o([Qb,kc],tc);o([Qb,ic],uc);o([Qb,dc],vc);o([wc,kc],xc);o([rc,ic],yc);o([rc,mc],zc);o([wc,dc],Ac);o([pc,dc],Bc);o([pc,mc],Cc);o([Dc,ic],Ec);o([Dc,gc],Fc);o([Qb,gc],Gc);o([wc,ic],Hc);o([Dc,mc],Ic);o([pc,kc],Jc);o([Dc,dc],Kc);o([rc,gc],Lc);o([Dc,kc],Mc);o([Qb,mc],Nc);o([rc,kc],Oc);o([wc,gc],Pc);o([wc,mc],Qc);o([pc,ic],Rc);s=m[r(Pb)][r(bc)];var t=s.indexOf(Sc);if(t!=-1){n=parseInt(s.substring(t+ab),kb);s=s.substring(_,t)}}catch(a){}showcase.__softPermutationId=n;return K(s+Tc)}
function M(){if(!u.__gwt_stylesLoaded){u.__gwt_stylesLoaded={}}A(Uc,V);A(Uc,Vc)}
I();showcase.__moduleBase=J();C[Y].moduleBase=showcase.__moduleBase;var N=L();if(u){var O=!!(u.location.protocol==Wc||u.location.protocol==Xc);u.__gwt_activeModules[Y].canRedirect=O;function P(){var b=Yc;try{u.sessionStorage.setItem(b,b);u.sessionStorage.removeItem(b);return true}catch(a){return false}}
if(O&&P()){var Q=Zc;var R=u.sessionStorage[Q];if(!/^http:\/\/(localhost|127\.0\.0\.1)(:\d+)?\/.*$/.test(R)){if(R&&(window.console&&console.log)){console.log($c+R)}R=gb}if(R&&!u[Q]){u[Q]=true;u[Q+_c]=J();var S=v.createElement(ob);S.src=R;var T=v.getElementsByTagName(tb)[_];T.insertBefore(S,T.firstElementChild||T.children[_]);return false}}}M();A(U,Vc);H(N);return true}
showcase.succeeded=showcase();