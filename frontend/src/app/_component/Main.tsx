import { AdBox, FlexBox, NavBar, OtherInfoElement } from '@/components';
import MainHeader from './MainHeader';

export default function Main() {
  const boxClass = 'rounded-[2rem] bg-custom-light-gray';

  return (
    <div className="w-full">
      <MainHeader></MainHeader>
      <div className="px-[1.2rem] flex flex-col gap-[1rem]">
        <div className={`h-[6.4rem] mt-[0.4rem] ${boxClass}`}></div>
        <FlexBox
          bottomChildren={
            <span className="custom-light-text">5천원 할인 받았다!</span>
          }
          topChildren={
            <OtherInfoElement
              imageSrc="/images/icon-dollar.png"
              leftExplainText="화장품"
              leftHighlightText="씨제이올리브영 주식회사"
              money="50,000 원"
              state="DOWN"
            />
          }
          isDivided={false}
        />
        <AdBox
          icon="/images/icon-calendar.png"
          link="/"
          mode="INTERACTIVE"
          subText="영수증을 등록하고 요술램프 혜택을 받아보세요!"
          title="내 가계부에 세부내역 추가하기"
        />
        <FlexBox
          bottomChildren={
            <OtherInfoElement
              imageSrc="/images/icon-dollar.png"
              leftExplainText="화장품"
              leftHighlightText="씨제이올리브영 주식회사"
              money="50,000 원"
              state="DOWN"
            />
          }
          isDivided
          mode="LIST"
          topChildren={
            <OtherInfoElement
              imageSrc="/images/icon-dollar.png"
              leftExplainText="화장품"
              leftHighlightText="씨제이올리브영 주식회사"
              money="50,000 원"
              state="DOWN"
            />
          }
        />
        <FlexBox
          bottomChildren={
            <OtherInfoElement
              imageSrc="/images/icon-dollar.png"
              leftExplainText="화장품"
              leftHighlightText="씨제이올리브영 주식회사"
              money="50,000 원"
              state="DOWN"
            />
          }
          isDivided
          mode="LIST"
          topChildren={
            <OtherInfoElement
              imageSrc="/images/icon-dollar.png"
              leftExplainText="화장품"
              leftHighlightText="씨제이올리브영 주식회사"
              money="50,000 원"
              state="DOWN"
            />
          }
        />
      </div>
      <NavBar mode="CHILD" />
    </div>
  );
}
