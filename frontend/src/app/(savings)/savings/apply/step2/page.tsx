'use client';

import { GuideText, ComplexInput, Select, Input, Button } from '@/components';

export default function ApplySavingsStep2() {
  return (
    <>
      <section className="w-full p-[1.2rem] flex flex-col gap-[2rem] relative">
        <GuideText text="정기적으로 매월 납부할 날짜와 금액을 설정해주세요." />

        <ComplexInput mode="NONE" label="적금 납부 일자">
          <Select mode="LOAN" min={1} max={30} />
        </ComplexInput>

        <ComplexInput mode="NONE" label="납부할 금액">
          <div className="flex gap-[1rem] items-center">
            <Input placeholder="1,000" />
            <span className="custom-semibold-text text-custom-light-purple">
              원
            </span>
          </div>
        </ComplexInput>
      </section>

      <div className="absolute w-full bottom-0 left-0 p-[1.2rem]">
        <Button mode="ACTIVE" label="완료" />
      </div>
    </>
  );
}
