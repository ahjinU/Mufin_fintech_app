'use client';

import { GuideText, Button, AgreeBottomSheet } from '@/components';
import { useRouter } from 'next/navigation';
import { useState } from 'react';
import dynamic from 'next/dynamic';
const Lottie = dynamic(() => import('react-lottie-player'), { ssr: false });
import lottieJson from '@/../public/lotties/piggy.json';

export default function MakeSavings() {
  const router = useRouter();
  const [isAgreeBottomSheetOpen, setIsAgreeBottomSheetOpen] = useState(false);

  return (
    <>
      <section className="w-full p-[1.2rem] flex flex-col gap-[3rem]">
        <GuideText text="적금 상품을 직접 만들 수 있어요!" />

        <div className="mx-auto">
          <Lottie
            loop
            animationData={lottieJson}
            play
            style={{ width: 250, height: 250 }}
          />
        </div>

        <p className="custom-medium-text">
          직접 만든 적금 상품은 아이들이 신청할 수 있어요!
        </p>
        <p className="custom-medium-text">
          아이들이 은행에 일정 기간 돈을 빌려주고, 이자를 얻어 나중에 돌려받을
          수 있는 습관을 들일 수 있죠.
        </p>
      </section>

      <div className="absolute w-full bottom-0 left-0 px-[1.2rem] py-[2rem]">
        <Button
          mode="ACTIVE"
          label="만들기"
          onClick={() => setIsAgreeBottomSheetOpen(true)}
        />
      </div>

      {isAgreeBottomSheetOpen && (
        <div className="absolute top-0 left-0 size-full bg-custom-black-with-opacity flex justify-center">
          <AgreeBottomSheet
            title="적금 상품 개설 동의"
            conditions={['개인 정보 수집 및 이용 동의', '적금 상품 개설 동의']}
            notice="적금 상품을 개설하려면 약관 동의가 필요해요!"
            isOpen={isAgreeBottomSheetOpen}
            handleClickCloseButton={() => setIsAgreeBottomSheetOpen(false)}
            handleClickConfirmButton={() => router.push('/savings/make/step1')}
          />
        </div>
      )}
    </>
  );
}
