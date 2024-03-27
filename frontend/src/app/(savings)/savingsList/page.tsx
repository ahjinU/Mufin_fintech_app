'use client';

import {
  BackButton,
  Header,
  FlexBox,
  MoneyInfoElement,
  PlusButton,
} from '@/components';

export default function SavingsList() {
  return (
    <section>
      <Header>
        <BackButton label="등록된 적금 상품" />
      </Header>

      <section className="p-[1.2rem] flex flex-col gap-[1rem]">
        <FlexBox
          isDivided={false}
          topChildren={
            <MoneyInfoElement
              leftHighlightText="아들아 너는 돈을 넣어라, 나는..."
              leftExplainText="적금 상품 이름"
              buttonOption="TINY_BUTTON"
              tinyButtonLabel="삭제하기"
              handleTinyButton={() => console.log('상품 삭제하기')}
            />
          }
        />

        <PlusButton label="적금 상품 더 만들기" />
      </section>
    </section>
  );
}
