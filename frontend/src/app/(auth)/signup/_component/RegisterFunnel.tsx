import { useEffect, useState } from 'react';
import { useFunnel } from '@/hooks/useFunnel';
import { useRouter } from 'next/router';

import UserInfo from './formgroup/UserInfo';

// import Name from './Name';
// import Gender from './Gender';
// import Birth from './Birth';
// import Email from './Email';
// import PassWord from './PassWord';
// import Address from './Address';

function RegisterFunnel() {
  const [registerData, setRegisterData] = useState({});
  const [Funnel, setStep] = useFunnel('NAME');
  const [location, setLocation] = useState();

  const router = useRouter();

  //   useEffect(() => {
  //     const asyncFunction = async data => {
  //       const latlng = await getLngLatFromAddress(data);
  //       setLocation(latlng);
  //     };
  //     if (registerData.userAddress) asyncFunction(registerData.userAddress);
  //   }, [registerData.userAddress]);

  return (
    <Funnel>
      <Funnel.Step name="NAME">
        <UserInfo
          onPrevious={() => {
            router('/login');
          }}
          onNext={(data) => {
            setStep('GENDER');
            setRegisterData({ ...registerData, userName: data });
          }}
          userInfo={registerData.userName}
        ></UserInfo>
      </Funnel.Step>
    </Funnel>
  );
}

export default RegisterFunnel;
