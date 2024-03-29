'use client';

import { AccountBox, ComplexInput, Select, Button } from '@/components';
import { useRouter } from 'next/navigation';
import { useState } from 'react';

// 몇 개월치를 납부할까요?에 상한은 밀린 횟수
export default function PaySavingsForm() {
  const router = useRouter();

  const [month, setMonth] = useState<number>();

  return (
    <>
      <section className="w-full p-[1.2rem] flex flex-col gap-[2rem] relative">
        <AccountBox
          text="내 계좌에서 돈이 빠져나가요!"
          isGrayBackground={true}
          money={192000}
        />

        <ComplexInput mode="NONE" label="납부가 완료된 개월 수">
          <span className="custom-medium-text">
            <span className="text-custom-purple">2</span>개월 / 6개월
          </span>
        </ComplexInput>

        <ComplexInput mode="NONE" label="약속한 1개월 납부 금액">
          <span className="custom-medium-text">5,000원</span>
        </ComplexInput>

        <ComplexInput
          mode="INFORM"
          label="몇 개월치를 납부할까요?"
          isMsg={true}
          message="오늘은 2개월치 납부할게요!"
        >
          <Select mode="SAVINGS_RETURN" min={1} max={3} setValue={setMonth} />
        </ComplexInput>
      </section>

      <div className="absolute w-full bottom-0 left-0 p-[1.2rem]">
        <Button
          mode="ACTIVE"
          label="납부하기"
          onClick={() => router.push('/savings/password')}
        />
      </div>
    </>
  );
}
