$wnd.showcase.runAsyncCallback11("function bRb(){}\nfunction dRb(){}\nfunction YQb(a,b){a.b=b}\nfunction ZQb(a){if(a==OQb){return true}iA();return a==RQb}\nfunction $Qb(a){if(a==NQb){return true}iA();return a==MQb}\nfunction cRb(a){this.b=(FSb(),ASb).a;this.e=(KSb(),JSb).a;this.a=a}\nfunction WQb(a,b){var c;c=vO(a.fb,160);c.b=b.a;!!c.d&&QLb(c.d,b)}\nfunction XQb(a,b){var c;c=vO(a.fb,160);c.e=b.a;!!c.d&&SLb(c.d,b)}\nfunction SQb(){SQb=m8;LQb=new bRb;OQb=new bRb;NQb=new bRb;MQb=new bRb;PQb=new bRb;QQb=new bRb;RQb=new bRb}\nfunction _Qb(){SQb();ULb.call(this);this.b=(FSb(),ASb);this.c=(KSb(),JSb);(EIb(),this.e)[_mc]=0;this.e[anc]=0}\nfunction TQb(a,b,c){var d;if(c==LQb){if(b==a.a){return}else if(a.a){throw I7(new E7b('Only one CENTER widget may be added'))}}Rh(b);P0b(a.j,b);c==LQb&&(a.a=b);d=new cRb(c);b.fb=d;WQb(b,a.b);XQb(b,a.c);VQb(a);Th(b,a)}\nfunction UQb(a){var b,c,d,e,f,g,h;w0b((EIb(),a.hb),'',yoc);g=new nfc;h=new Z0b(a.j);while(h.b<h.c.c){b=X0b(h);f=vO(b.fb,160).a;d=vO(vac(Ffc(g.d,f)),84);c=!d?1:d.a;e=f==PQb?'north'+c:f==QQb?'south'+c:f==RQb?'west'+c:f==MQb?'east'+c:f==OQb?'linestart'+c:f==NQb?'lineend'+c:Glc;w0b(Qo(b.hb),yoc,e);Hac(g,f,R7b(c+1))}}\nfunction VQb(a){var b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r;b=(EIb(),a.d);while(gKb(b)>0){wo(b,fKb(b,0))}o=1;e=1;for(i=new Z0b(a.j);i.b<i.c.c;){d=X0b(i);f=vO(d.fb,160).a;f==PQb||f==QQb?++o:(f==MQb||f==RQb||f==OQb||f==NQb)&&++e}p=EN(e2,Vic,270,o,0,1);for(g=0;g<o;++g){p[g]=new dRb;p[g].b=$doc.createElement(Zmc);so(b,LIb(p[g].b))}k=0;l=e-1;m=0;q=o-1;c=null;for(h=new Z0b(a.j);h.b<h.c.c;){d=X0b(h);j=vO(d.fb,160);r=$doc.createElement($mc);j.d=r;j.d[Mmc]=j.b;j.d.style[Nmc]=j.e;j.d[ljc]=j.f;j.d[kjc]=j.c;if(j.a==PQb){HIb(p[m].b,r,p[m].a);so(r,LIb(d.hb));r[Jnc]=l-k+1;++m}else if(j.a==QQb){HIb(p[q].b,r,p[q].a);so(r,LIb(d.hb));r[Jnc]=l-k+1;--q}else if(j.a==LQb){c=r}else if(ZQb(j.a)){n=p[m];HIb(n.b,r,n.a++);so(r,LIb(d.hb));r[zoc]=q-m+1;++k}else if($Qb(j.a)){n=p[m];HIb(n.b,r,n.a);so(r,LIb(d.hb));r[zoc]=q-m+1;--l}}if(a.a){n=p[m];HIb(n.b,c,n.a);so(c,LIb(eh(a.a)))}}\nvar yoc='cwDockPanel';l8(427,1,Flc);_.Bc=function _qb(){var a,b,c;Bab(this.a,(a=new _Qb,(EIb(),a.hb).className='cw-DockPanel',a.e[_mc]=4,YQb(a,(FSb(),zSb)),TQb(a,new yPb(soc),(SQb(),PQb)),TQb(a,new yPb(toc),QQb),TQb(a,new yPb(uoc),MQb),TQb(a,new yPb(voc),RQb),TQb(a,new yPb(woc),PQb),TQb(a,new yPb(xoc),QQb),b=new yPb(\"Voici un <code>panneau de d\\xE9filement<\\/code> situ\\xE9 au centre d'un <code>panneau d'ancrage<\\/code>. Si des contenus relativement volumineux sont ins\\xE9r\\xE9s au milieu de ce panneau \\xE0 d\\xE9filement et si sa taille est d\\xE9finie, il prend la forme d'une zone dot\\xE9e d'une fonction de d\\xE9filement \\xE0 l'int\\xE9rieur de la page, sans l'utilisation d'un IFRAME.<br><br>Voici un texte encore plus obscur qui va surtout servir \\xE0 faire d\\xE9filer cet \\xE9l\\xE9ment jusqu'en bas de sa zone visible. Sinon, il vous faudra r\\xE9duire ce panneau \\xE0 une taille minuscule pour pouvoir afficher ces formidables barres de d\\xE9filement!\"),c=new TMb(b),c.hb.style[ljc]='400px',c.hb.style[kjc]='100px',TQb(a,c,LQb),UQb(a),a))};l8(883,262,qjc,_Qb);_.gc=function aRb(a){var b;b=OKb(this,a);if(b){a==this.a&&(this.a=null);VQb(this)}return b};var LQb,MQb,NQb,OQb,PQb,QQb,RQb;var f2=i7b(ojc,'DockPanel',883);l8(159,1,{},bRb);var c2=i7b(ojc,'DockPanel/DockLayoutConstant',159);l8(160,1,{160:1},cRb);_.c='';_.f='';var d2=i7b(ojc,'DockPanel/LayoutData',160);l8(270,1,{270:1},dRb);_.a=0;var e2=i7b(ojc,'DockPanel/TmpRow',270);Aic(zl)(11);\n//# sourceURL=showcase-11.js\n")
