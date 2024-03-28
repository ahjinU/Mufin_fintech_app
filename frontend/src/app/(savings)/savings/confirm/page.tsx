'use client';

import { BackButton, Header, Accordion } from '@/components';

export default function ConfirmSavings() {
  // 부모 입장에서 적금 현황 확인하기 페이지
  return (
    <>
      <Header>
        <BackButton label="적금 현황 확인하기" />
      </Header>
      <section className="w-full p-[1.2rem] flex flex-col gap-[1rem] relative">
        <Accordion
          contents={[
            ['적금 가입일', '2024-01-02'],
            ['적금 만기일', '2024-07-02'],
            ['남은 달 수', '4달'],
            ['현재까지 이체금액', '60,000원'],
            ['적금 이자율', '0.05%'],
          ]}
          mode="NORMAL"
          name="김지니"
          title="는 순조롭게 적금을 드는 중..."
        />

        <Accordion
          contents={[
            ['가입일', '2024-01-02'],
            ['모르겠어', '뭐넣지'],
          ]}
          mode="EXCEPTIONAL"
          name="김민니"
          title="는 상환이 두 번이나 밀렸슈"
        />
      </section>
    </>
  );
}
