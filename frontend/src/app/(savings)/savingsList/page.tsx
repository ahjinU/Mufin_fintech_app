'use client';

import { useRouter } from 'next/navigation';
import {
  BackButton,
  Header,
  FlexBox,
  MoneyInfoElement,
  PlusButton,
  AlertConfirmModal,
} from '@/components';

export default function SavingsList() {
  const router = useRouter();

  return (
    <section>
      <Header>
        <BackButton label="등록된 적금 상품" />
      </Header>

      <section className="w-full p-[1.2rem] flex flex-col gap-[1rem]">
        <FlexBox
          isDivided={false}
          topChildren={
            <MoneyInfoElement
              leftHighlightText="아들아 너는 돈을 넣어라, 나는..."
              leftExplainText="적금"
              buttonOption="TINY_BUTTON"
              tinyButtonLabel="삭제하기"
              handleTinyButton={() => router.replace('/check')}
            />
          }
        />

        <PlusButton label="적금 상품 더 만들기" />
      </section>
    </section>
  );
}
