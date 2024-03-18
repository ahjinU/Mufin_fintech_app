import SubGuideText from '../SubGuideText/SubGuideText';
import MoneyInfoElement from '../MoneyInfoElement/MoneyInfoElement';

interface AccountBoxProps {
  text: string;
}

export default function AccountBox({ text, ...props }: AccountBoxProps) {
  return (
    <section
      className="w-full flex flex-col gap-[1.4rem] px-[2rem] py-[2.2rem] bg-custom-white rounded-[2rem]"
      {...props}
    >
      <SubGuideText text="아래 내 계좌에서 돈이 빠져나가요!" />
      <MoneyInfoElement
        imageSrc="http://localhost:3000/images/icon-dollar.png"
        leftExplainText="내 잔액"
        leftHighlightText="192,000 원"
        buttonOption="RIGHT_ARROW"
      />
    </section>
  );
}
