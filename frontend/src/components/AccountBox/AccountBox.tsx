import SubGuideText from '../SubGuideText/SubGuideText';
import MoneyInfoElement from '../MoneyInfoElement/MoneyInfoElement';
import { commaNum } from '@/utils/commaNum';
import useUserStore from '@/app/_store/store';

interface AccountBoxProps {
  isGrayBackground?: boolean;
}

export default function AccountBox({
  isGrayBackground,
  ...props
}: AccountBoxProps) {
  const { userData } = useUserStore.getState();

  return (
    <section
      className={`w-full flex flex-col gap-[1.4rem] px-[2rem] py-[2.2rem] ${
        isGrayBackground ? 'bg-custom-light-gray' : 'bg-custom-white'
      } rounded-[2rem]`}
      {...props}
    >
      <SubGuideText text="아래 내 계좌에서 돈이 빠져나가요!" />
      <MoneyInfoElement
        imageSrc="/images/icon-dollar.png"
        leftExplainText="내 입출금 계좌 잔액"
        leftHighlightText={`${commaNum(userData.balance)} 원`}
        buttonOption="NO"
      />
    </section>
  );
}
