'use client';

import dynamic from 'next/dynamic';
const Lottie = dynamic(() => import('react-lottie-player'), { ssr: false });
import lottieJson from '@/../public/lotties/404.json';

import { Button } from '@/components';
import { useRouter } from 'next/navigation';

export default function NotFound() {
  const router = useRouter();

  return (
    <div className="relative w-[36rem] h-[64rem] pt-[5rem]">
      <section className="w-full mx-auto flex flex-col gap-[1rem]">
        <h3 className="custom-bold-text ml-[1rem] mb-[1rem]">
          페이지를 찾을 수 없습니다.
        </h3>
        <Lottie
          loop
          animationData={lottieJson}
          play
          style={{ width: 360, height: 360 }}
        />
      </section>

      <div className="w-full absolute p-[1rem] left-0 bottom-0">
        <Button
          mode="ACTIVE"
          label="홈으로 이동하기"
          onClick={() => router.push('/')}
        />
      </div>
    </div>
  );
}
