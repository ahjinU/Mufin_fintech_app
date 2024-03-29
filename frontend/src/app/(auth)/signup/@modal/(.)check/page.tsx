'use client';

import useRegisterStore from '../../_store/store';
import AlertConfirm from '@/components/AlertConfirmModal/AlertConfirmModal';
import { useRouter } from 'next/navigation';
import { signUpParent, signUpChild } from '../../_apis/apis';

export default function Check() {
  const { registerData } = useRegisterStore();
  const { name, gender, birth, address, address2, password } = registerData;

  const router = useRouter();

  const signUp = async () => {
    try {
      const fetchedData = await signUpParent(
        name,
        gender,
        birth,
        address,
        address2,
        password,
      );
      if (fetchedData.ok) {
        router.push('/signup/complete');
      } else {
        console.log('회원가입 토큰 문제', fetchedData);
      }
    } catch (error) {
      console.error('회원가입 에러', error);
    }
  };

  return (
    <div className="absolute top-0 left-0 size-full flex justify-center bg-custom-black-with-opacity">
      <AlertConfirm
        isOpen
        handleClickOkay={signUp}
        handleClickNo={() => {
          router.back();
        }}
        text="회원가입을 진행하시겠어요?"
      />
    </div>
  );
}
