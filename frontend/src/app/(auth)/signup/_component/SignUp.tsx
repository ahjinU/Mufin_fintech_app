'use client';

import { useState } from 'react';
import { GuideText, ProgressBar } from '@/components';
import useRegisterStore from '../_store/store';
import {
  UserInfoType,
  UserContactType,
  UserAccountType,
} from '../_types/types';

import UserInfo from './pagegroup/UserInfo';
import UserContact from './pagegroup/UserContact';
import UserAccount from './pagegroup/UserAccount';

interface SignUpProps {
  forParent: boolean;
}

export default function SignUp({ forParent }: SignUpProps) {
  const [state, setState] = useState('first');
  const [barGage, setBarGage] = useState(100 / 3);
  const { registerData, setRegisterData } = useRegisterStore();

  const firstPage = {
    onNext: (data: UserInfoType) => {
      setState('second');
      setBarGage((100 / 3) * 2);
      setRegisterData({
        ...registerData,
        name: data.name,
        gender: data.gender,
        birth: data.birth,
      });
    },
  };

  const secondPage = {
    onNext: (data: UserContactType) => {
      setState('third');
      setBarGage(100);
      setRegisterData({
        ...registerData,
        telephone: data.telephone,
        address: data.address,
        address2: data.address2,
      });
    },
  };

  const thirdPage = {
    onNext: (data: UserAccountType) => {
      setRegisterData({
        ...registerData,
        email: data.email,
        password: data.password,
      });
    },
  };

  return (
    <div className="flex flex-col gap-[2rem]">
      {forParent ? (
        <GuideText text="부모님 먼저 가입한 후 아이도 회원가입할 수 있어요!" />
      ) : (
        <GuideText text="아이를 위한 회원가입이에요!" />
      )}
      <ProgressBar barGage={barGage} />
      {state == 'first' ? (
        <UserInfo onNext={firstPage.onNext} />
      ) : state == 'second' ? (
        <UserContact onNext={secondPage.onNext} forParent={forParent} />
      ) : (
        <UserAccount onNext={thirdPage.onNext} forParent={forParent} />
      )}
    </div>
  );
}
