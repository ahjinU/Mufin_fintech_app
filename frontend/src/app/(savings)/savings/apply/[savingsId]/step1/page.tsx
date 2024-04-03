'use client';

import { GuideText, Button, AgreeBottomSheet } from '@/components';
import { useState } from 'react';
import { useRouter, useParams } from 'next/navigation';
import Lottie from 'react-lottie-player';
import lottieJson from '../../../../../../../public/lotties/piggy.json';

export default function MakeSavings() {
  const router = useRouter();
  const params = useParams<{ savingsId: string }>();
  const [isAgreeBottomSheetOpen, setIsAgreeBottomSheetOpen] =
    useState<boolean>(false);

  return (
    <>
      <section className="w-full p-[1.2rem] flex flex-col gap-[3rem] relative">
        <GuideText text="부모님이 만든 적금 상품에 가입할 수 있어요!" />

        <div className="mx-auto">
          <Lottie
            loop
            animationData={lottieJson}
            play
            style={{ width: 250, height: 250 }}
          />
        </div>

        <p className="custom-medium-text">
          부모님께 잠시 얼마를 맡겨 두세요! 정해진 기간 동안 꾸준히 이체하면
          돈이 더 늘어나서 돌아올 거에요.
        </p>
        <p className="custom-medium-text">
          나중에 은행에 일정 기간 돈을 빌려주는 대신 이자와 함께 원금을 돌려받을
          수 있는 습관을 들일 수 있어요.
        </p>
      </section>

      <div className="absolute w-full bottom-0 left-0 px-[1.2rem] py-[3rem]">
        <Button
          mode="ACTIVE"
          label="가입하기"
          onClick={() => setIsAgreeBottomSheetOpen(true)}
        />
      </div>

      {isAgreeBottomSheetOpen && (
        <div className="absolute top-0 left-0 size-full bg-custom-black-with-opacity flex justify-center">
          <AgreeBottomSheet
            title="적금 상품 가입 동의"
            conditions={['개인 정보 수집 및 이용 동의', '적금 상품 가입 동의']}
            notice="적금 상품을 가입하려면 약관 동의가 필요해요!"
            isOpen={isAgreeBottomSheetOpen}
            handleClickCloseButton={() => setIsAgreeBottomSheetOpen(false)}
            handleClickConfirmButton={() =>
              router.push(`/savings/apply/${params?.savingsId}/step2`)
            }
          />
        </div>
      )}
    </>
  );
}
