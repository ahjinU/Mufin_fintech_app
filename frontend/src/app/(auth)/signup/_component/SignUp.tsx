'use client';

import { useState } from 'react';
import { GuideText, ProgressBar } from '@/components';

import {
  UserInfoType,
  UserContactType,
  UserAccountType,
} from '../_types/types';

import UserInfo from './formgroup/UserInfo';
import UserContact from './formgroup/UserContact';
import UserAccount from './formgroup/UserAccount';

const initialData = {
  name: '',
  gender: '',
  birth: '',
  telephone: '',
  address: '',
  address2: '',
  email: '',
  password: '',
};

export default function SignUp() {
  const [state, setState] = useState('first');
  const [barGage, setBarGage] = useState(100 / 3);
  const [registerData, setRegisterData] = useState(initialData);

  const firstPage = {
    onNext: (data: UserInfoType) => {
      setState('second');
      setBarGage((100 / 3) * 2);
      setRegisterData((prevData) => ({
        ...prevData,
        name: data.name,
        gender: data.gender,
        birth: data.birth,
      }));
    },
  };

  const secondPage = {
    onNext: (data: UserContactType) => {
      setState('third');
      setBarGage(100);
      setRegisterData((prevData) => ({
        ...prevData,
        telephone: data.telephone,
        address: data.address,
        address2: data.address2,
      }));
    },
  };

  const thirdPage = {
    onNext: (data: UserAccountType) => {
      setRegisterData((prevData) => ({
        ...prevData,
        email: data.email,
        password: data.password,
      }));
    },
  };

  return (
    <div className="flex flex-col gap-[2rem]">
      <GuideText text="부모님 먼저 가입한 후 아이도 회원가입할 수 있어요!" />
      <ProgressBar barGage={barGage} />
      {state == 'first' ? (
        <UserInfo onNext={firstPage.onNext} />
      ) : state == 'second' ? (
        <UserContact onNext={secondPage.onNext} />
      ) : (
        <UserAccount onNext={thirdPage.onNext} />
      )}
    </div>
  );
}
