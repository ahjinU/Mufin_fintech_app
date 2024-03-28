'use client';

import { GuideText, Button, AgreeBottomSheet } from '@/components';
import Image from 'next/image';
import { useState } from 'react';

export default function MakeSavings() {
  // const [isBottomSheetOpen, setIsBottomSheetOpen] = useState<boolean>(false);

  return (
    <>
      <section className="w-full p-[1.2rem] flex flex-col gap-[3rem] relative">
        <GuideText text="부모님이 만든 적금 상품에 가입할 수 있어요!" />

        <div className="mx-auto">
          <Image
            src={'/images/icon-piggy-bank.png'}
            width={200}
            height={200}
            alt={'돼지 저금통'}
            className="w-[20rem] h-[20rem]"
          />
        </div>

        <p className="custom-medium-text">
          부모님께 잠시 얼마를 맡겨 두세요! 정해진 기간 동안 꾸준히 이체하면
          돈이 더 늘어나서 돌아올 거에요.
        </p>
        <p className="custom-medium-text">
          나중에 은행에 일정 기간 돈을 빌려주는 대신 이자와 함께 원금을 돌려받을
          수 있는 습관을 들일 수 있어요!
        </p>
      </section>

      {/* <AgreeBottomSheet
        title="적금 상품 개설 동의"
        notice="적금 상품을 개설하려면 아래 약관 동의가 필요해요."
        conditions={['개인 정보 수집 및 이용 동의', '적금 상품 개설 동의']}
        isOpen={false}
      /> */}

      <div className="absolute w-full bottom-0 left-0 p-[1.2rem]">
        <Button mode="ACTIVE" label="가입하기" />
      </div>
    </>
  );
}
