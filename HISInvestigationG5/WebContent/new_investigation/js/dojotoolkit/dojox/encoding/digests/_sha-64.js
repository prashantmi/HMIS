//>>built
define("dojox/encoding/digests/_sha-64",["./_sha-32"],function(_1){
var _2=function(h,l){
return {h:h,l:l};
};
function _3(_4,_5){
_4.h=_5.h;
_4.l=_5.l;
};
function _6(_7,x,_8){
_7.l=(x.l>>>_8)|(x.h<<(32-_8));
_7.h=(x.h>>>_8)|(x.l<<(32-_8));
};
function _9(_a,x,_b){
_a.l=(x.h>>>_b)|(x.l<<(32-_b));
_a.h=(x.l>>>_b)|(x.h<<(32-_b));
};
function _c(_d,x,_e){
_d.l=(x.l>>>_e)|(x.h<<(32-_e));
_d.h=(x.h>>>_e);
};
function _f(dst,x,y){
var w0=(x.l&65535)+(y.l&65535);
var w1=(x.l>>>16)+(y.l>>>16)+(w0>>>16);
var w2=(x.h&65535)+(y.h&65535)+(w1>>>16);
var w3=(x.h>>>16)+(y.h>>>16)+(w2>>>16);
dst.l=(w0&65535)|(w1<<16);
dst.h=(w2&65535)|(w3<<16);
};
function _10(dst,a,b,c,d){
var w0=(a.l&65535)+(b.l&65535)+(c.l&65535)+(d.l&65535);
var w1=(a.l>>>16)+(b.l>>>16)+(c.l>>>16)+(d.l>>>16)+(w0>>>16);
var w2=(a.h&65535)+(b.h&65535)+(c.h&65535)+(d.h&65535)+(w1>>>16);
var w3=(a.h>>>16)+(b.h>>>16)+(c.h>>>16)+(d.h>>>16)+(w2>>>16);
dst.l=(w0&65535)|(w1<<16);
dst.h=(w2&65535)|(w3<<16);
};
function _11(dst,a,b,c,d,e){
var w0=(a.l&65535)+(b.l&65535)+(c.l&65535)+(d.l&65535)+(e.l&65535);
var w1=(a.l>>>16)+(b.l>>>16)+(c.l>>>16)+(d.l>>>16)+(e.l>>>16)+(w0>>>16);
var w2=(a.h&65535)+(b.h&65535)+(c.h&65535)+(d.h&65535)+(e.h&65535)+(w1>>>16);
var w3=(a.h>>>16)+(b.h>>>16)+(c.h>>>16)+(d.h>>>16)+(e.h>>>16)+(w2>>>16);
dst.l=(w0&65535)|(w1<<16);
dst.h=(w2&65535)|(w3<<16);
};
var K=[_2(1116352408,3609767458),_2(1899447441,602891725),_2(3049323471,3964484399),_2(3921009573,2173295548),_2(961987163,4081628472),_2(1508970993,3053834265),_2(2453635748,2937671579),_2(2870763221,3664609560),_2(3624381080,2734883394),_2(310598401,1164996542),_2(607225278,1323610764),_2(1426881987,3590304994),_2(1925078388,4068182383),_2(2162078206,991336113),_2(2614888103,633803317),_2(3248222580,3479774868),_2(3835390401,2666613458),_2(4022224774,944711139),_2(264347078,2341262773),_2(604807628,2007800933),_2(770255983,1495990901),_2(1249150122,1856431235),_2(1555081692,3175218132),_2(1996064986,2198950837),_2(2554220882,3999719339),_2(2821834349,766784016),_2(2952996808,2566594879),_2(3210313671,3203337956),_2(3336571891,1034457026),_2(3584528711,2466948901),_2(113926993,3758326383),_2(338241895,168717936),_2(666307205,1188179964),_2(773529912,1546045734),_2(1294757372,1522805485),_2(1396182291,2643833823),_2(1695183700,2343527390),_2(1986661051,1014477480),_2(2177026350,1206759142),_2(2456956037,344077627),_2(2730485921,1290863460),_2(2820302411,3158454273),_2(3259730800,3505952657),_2(3345764771,106217008),_2(3516065817,3606008344),_2(3600352804,1432725776),_2(4094571909,1467031594),_2(275423344,851169720),_2(430227734,3100823752),_2(506948616,1363258195),_2(659060556,3750685593),_2(883997877,3785050280),_2(958139571,3318307427),_2(1322822218,3812723403),_2(1537002063,2003034995),_2(1747873779,3602036899),_2(1955562222,1575990012),_2(2024104815,1125592928),_2(2227730452,2716904306),_2(2361852424,442776044),_2(2428436474,593698344),_2(2756734187,3733110249),_2(3204031479,2999351573),_2(3329325298,3815920427),_2(3391569614,3928383900),_2(3515267271,566280711),_2(3940187606,3454069534),_2(4118630271,4000239992),_2(116418474,1914138554),_2(174292421,2731055270),_2(289380356,3203993006),_2(460393269,320620315),_2(685471733,587496836),_2(852142971,1086792851),_2(1017036298,365543100),_2(1126000580,2618297676),_2(1288033470,3409855158),_2(1501505948,4234509866),_2(1607167915,987167468),_2(1816402316,1246189591)];
var o={outputTypes:_1.outputTypes,stringToUtf8:function(s){
return _1.stringToUtf8(s);
},toWord:function(s){
return _1.toWord(s);
},toHex:function(wa){
return _1.toHex(wa);
},toBase64:function(wa){
return _1.toBase64(wa);
},_toString:function(wa){
return _1._toString(wa);
}};
o.digest=function(msg,_12,_13,_14){
var _15=[];
for(var i=0,l=_13.length;i<l;i+=2){
_15.push(_2(_13[i],_13[i+1]));
}
var T1=_2(0,0),T2=_2(0,0),a=_2(0,0),b=_2(0,0),c=_2(0,0),d=_2(0,0),e=_2(0,0),f=_2(0,0),g=_2(0,0),h=_2(0,0),s0=_2(0,0),s1=_2(0,0),Ch=_2(0,0),Maj=_2(0,0),r1=_2(0,0),r2=_2(0,0),r3=_2(0,0);
var j,i;
var w=new Array(80);
for(i=0;i<80;i++){
w[i]=_2(0,0);
}
msg[_12>>5]|=128<<(24-(_12&31));
msg[((_12+128>>10)<<5)+31]=_12;
for(i=0;i<msg.length;i+=32){
_3(a,_15[0]);
_3(b,_15[1]);
_3(c,_15[2]);
_3(d,_15[3]);
_3(e,_15[4]);
_3(f,_15[5]);
_3(g,_15[6]);
_3(h,_15[7]);
for(j=0;j<16;j++){
w[j].h=msg[i+2*j];
w[j].l=msg[i+2*j+1];
}
for(j=16;j<80;j++){
_6(r1,w[j-2],19);
_9(r2,w[j-2],29);
_c(r3,w[j-2],6);
s1.l=r1.l^r2.l^r3.l;
s1.h=r1.h^r2.h^r3.h;
_6(r1,w[j-15],1);
_6(r2,w[j-15],8);
_c(r3,w[j-15],7);
s0.l=r1.l^r2.l^r3.l;
s0.h=r1.h^r2.h^r3.h;
_10(w[j],s1,w[j-7],s0,w[j-16]);
}
for(j=0;j<80;j++){
Ch.l=(e.l&f.l)^(~e.l&g.l);
Ch.h=(e.h&f.h)^(~e.h&g.h);
_6(r1,e,14);
_6(r2,e,18);
_9(r3,e,9);
s1.l=r1.l^r2.l^r3.l;
s1.h=r1.h^r2.h^r3.h;
_6(r1,a,28);
_9(r2,a,2);
_9(r3,a,7);
s0.l=r1.l^r2.l^r3.l;
s0.h=r1.h^r2.h^r3.h;
Maj.l=(a.l&b.l)^(a.l&c.l)^(b.l&c.l);
Maj.h=(a.h&b.h)^(a.h&c.h)^(b.h&c.h);
_11(T1,h,s1,Ch,K[j],w[j]);
_f(T2,s0,Maj);
_3(h,g);
_3(g,f);
_3(f,e);
_f(e,d,T1);
_3(d,c);
_3(c,b);
_3(b,a);
_f(a,T1,T2);
}
_f(_15[0],_15[0],a);
_f(_15[1],_15[1],b);
_f(_15[2],_15[2],c);
_f(_15[3],_15[3],d);
_f(_15[4],_15[4],e);
_f(_15[5],_15[5],f);
_f(_15[6],_15[6],g);
_f(_15[7],_15[7],h);
}
var ret=[];
if(_14==384){
_15.length=6;
}
for(var i=0,l=_15.length;i<l;i++){
ret[i*2]=_15[i].h;
ret[i*2+1]=_15[i].l;
}
return ret;
};
return o;
});
