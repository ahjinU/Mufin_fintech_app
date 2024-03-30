'use client';

import {
  BackButton,
  Header,
  FlexBox,
  MoneyInfoElement,
  TinyButton,
  MoneyShow,
} from '@/components';
import Image from 'next/image';

function ContentRow({ keyName, value }: { keyName: string; value: string }) {
  return (
    <section className="flex justify-between custom-medium-text">
      <span>{keyName}</span>
      <span>{value}</span>
    </section>
  );
}

export default function MySavings() {
  // 아이의 적금 보기
  return (
    <>
      <Header>
        <BackButton label="나의 적금"></BackButton>
      </Header>
      <section className="w-full p-[1.2rem] flex flex-col gap-[1rem] relative">
        <MoneyShow
          mode="UNDIVIDED"
          text={['적금 누적 총액']}
          money={['20,1000 ']}
          unit="원"
        />

        <div className="w-fit flex gap-[0.5rem] items-center custom-medium-text self-end">
          <Image
            src="/images/icon-smile.png"
            width={16}
            height={16}
            alt="정상 납부"
          />
          <span className="text-custom-blue">정상 납부 중</span>
          <Image
            src="/images/icon-sad.png"
            width={16}
            height={16}
            alt="정상 납부"
          />
          <span className="text-custom-red">연체 횟수 남음</span>
        </div>

        <FlexBox
          isDivided={true}
          topChildren={
            <MoneyInfoElement
              imageSrc="/images/icon-smile.png"
              leftExplainText="아들아 너는 돈을 내거라, 나는 이자를..."
              leftHighlightText="12,000원"
              buttonOption="NO"
            />
          }
          bottomChildren={
            <div className="flex gap-[1rem] self-end">
              <TinyButton label="납부하기" />
              <TinyButton isWarning={true} label="중도 해지하기" />
            </div>
          }
        />
      </section>
    </>
  );
}
