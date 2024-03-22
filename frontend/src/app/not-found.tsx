'use client';

import Lottie from 'react-lottie-player';
import lottieJson from '../../public/lotties/404.json';
import { Button } from '@/components';

export default function NotFound() {
  return (
    <div className="relative w-[36rem] h-[64rem] pt-[5rem]">
      <section className="w-[30rem] mx-auto flex flex-col gap-[1rem]">
        <h3 className="custom-bold-text">페이지를 찾을 수 없습니다.</h3>
        <Lottie
          loop
          animationData={lottieJson}
          play
          style={{ width: 300, height: 300 }}
        />
      </section>

      <div className="w-full absolute p-[1rem] left-0 bottom-0">
        <Button mode="ACTIVE" label="홈으로 이동하기" />
      </div>
    </div>
  );
}
