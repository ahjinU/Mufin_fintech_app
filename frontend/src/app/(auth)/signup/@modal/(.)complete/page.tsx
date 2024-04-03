'use client';

import Link from 'next/link';
import { Button, InfoShow } from '@/components';
import useRegisterStore from '../../_store/store';
import useUserStore from '@/app/_store/store';

import dynamic from 'next/dynamic';
const Lottie = dynamic(() => import('react-lottie-player'), { ssr: false });
import lottieJson from '@/../public/lotties/celebration.json';

export default function Complete() {
  const { registerData } = useRegisterStore();
  const { userData } = useUserStore();

  return (
    <div className="absolute top-0 left-0 size-full bg-custom-white p-[4rem] flex flex-col gap-[8rem]">
      <div className="flex flex-col gap-[1rem]">
        <p className="custom-bold-text text-custom-black">
          {userData.isParent ? '아이 회원가입 완료!' : '회원가입 완료!'}
        </p>
        <div className="custom-medium-text text-custom-dark-gray">
          <p>회원가입이 완료되었습니다!</p>
          <p>
            {userData.isParent
              ? '환영합니다! 회원가입과 로그인이 완료됐어요. 서비스를 이용하기 전에 반드시 아래 버튼을 눌러 계좌를 생성해주세요 :D'
              : '환영합니다! 아이의 회원가입을 완료했어요. 아이에게 아래 계정 정보를 전달해주고, 로그인 후 계좌를 개설해주세요 :D'}
          </p>
          {userData.isParent ? null : (
            <>
              <InfoShow
                label={'이름'}
                text={registerData.name}
                copyIcon={false}
              />
              <InfoShow
                label={'이메일'}
                text={registerData.email}
                copyIcon={true}
              />
              <InfoShow
                label={'비밀번호'}
                text={registerData.password}
                copyIcon={true}
              />
            </>
          )}
        </div>
      </div>
      <Lottie
        loop
        animationData={lottieJson}
        play
        style={{ width: 200, height: 200 }}
        className="self-center"
      />
      <div className="fixed bottom-0 inset-x-0 px-[1.2rem] py-[3rem]">
        <Link href={userData.isParent ? '/account' : '/'} replace>
          <Button
            mode={'ACTIVE'}
            label={userData.isParent ? '계좌 만들기' : '홈으로 돌아가기'}
          ></Button>
        </Link>
      </div>
    </div>
  );
}
