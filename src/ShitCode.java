public class ShitCode {

    //Code like this that prematurely turns programmers into managers
    public int nthUglyNumber(int _______) {
        if(_______ == 0)
            return 0;
        long[] _____ = new long[_______];
        _____[0] = 1;
        for(int __=1; __<_______; __++) {
            long ____ = Long.MAX_VALUE;
            for(int ___=__-1; ___>=0; ___--) {
                long ______ = Long.MAX_VALUE;
                if(_____[__-1] < _____[___] * 2)
                    ______ = _____[___] * 2;
                else if(_____[__-1] < _____[___] * 3)
                    ______ = _____[___] * 3;
                else if(_____[__-1] < _____[___] * 5)
                    ______ = _____[___] * 5;
                else break;
                
                long ________ = ______;
                if(________ > _____[__-1] && ____ > ________) {
                    ____ = ________;
                }
            }
            _____[__] = ____;
        }
        return (int) _____[_______-1];
//        return 0;
    }
}
