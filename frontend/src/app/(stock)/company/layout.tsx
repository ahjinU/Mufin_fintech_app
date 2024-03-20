import { BackButton } from '@/components';
import { GuideAccordion } from '@/components';

export default function CompanyLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <section className="px-[1.9rem] py-[1.2rem] flex flex-col gap-[1rem]">
      <header>
        <BackButton label="바람막이 회사" link="/" />
      </header>
      <GuideAccordion
        icon="/images/icon-bulb.png"
        title="주식의 가격은 왜 바뀌어요?"
        children={
          <p>
            주식의 가격은 사람들의 구매와 판매에 의해 결정돼요! 많은 사람들이
            주식을 구매하면 가격이 상승하고 반대로, 많은 사람들이 판매하면
            가격이 하락해요. 이로 인해 실시간으로, 지속적으로 주식의 가격은
            변동돼요.
          </p>
        }
      />
    </section>
  );
}
