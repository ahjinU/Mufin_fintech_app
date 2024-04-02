'use client';

import { Button } from '@/components';
import Link from 'next/link';

import Lottie from 'react-lottie-player';
import lottieJson from '../../../../../../public/lotties/account.json';

export default function Complete() {
  return (
    <div className="absolute top-0 left-0 size-full p-[4rem] bg-custom-white flex flex-col gap-[2rem]">
      <p className="custom-bold-text text-custom-black">계좌 개설 완료!</p>
      <div className="custom-medium-text text-custom-black">
        <p>머핀 은행의 계좌를 정상적으로 개설했습니다.</p>
        <p>머핀에서 재미있고 유익한 금융 생활을 함께 해요 :D</p>
      </div>
      <Lottie
        loop
        animationData={lottieJson}
        play
        style={{ width: 240, height: 240 }}
        className="mt-[4rem] self-center"
      />
      <div className="fixed bottom-0 inset-x-0 p-[1.2rem]">
        <Link href="/" replace>
          <Button mode={'ACTIVE'} label={'홈으로 가기'}></Button>
        </Link>
      </div>
    </div>
  );
}
