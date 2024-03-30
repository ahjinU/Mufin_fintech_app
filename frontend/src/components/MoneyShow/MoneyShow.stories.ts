import type { Meta, StoryObj } from '@storybook/react';

import MoneyShow from './MoneyShow';

const meta = {
  title: 'Common/MoneyShow',
  component: MoneyShow,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    mode: {
      description:
        'DIVIDED, DIVIDED_GRAY는 서로 배경 색이 다르고 두 개로 나누어졌고 UNDIVIDED 나누어지지 않은 모드',
    },
    text: {
      description: '텍스트 입력',
    },
    money: {
      description: '돈이나 몇 주인지 입력',
    },
    unit: {
      description: '단위 입력(원, 달러, 주 등)',
    },
    moneyColor: {
      description: '돈의 숫자 색 바꿀 경우 입력',
    },
  },
} satisfies Meta<typeof MoneyShow>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Divided: Story = {
  args: {
    mode: 'DIVIDED',
    text: ['지출', '수입'],
    money: ['-5,000', '+6,000'],
    unit: '원',
  },
};

export const DividedGray: Story = {
  args: {
    mode: 'DIVIDED_GRAY',
    text: ['판매대기', '구매대기'],
    money: ['83', '43'],
    unit: '주',
    moneyColor: ['text-custom-blue', 'text-custom-red'],
  },
};

export const Undivided: Story = {
  args: {
    mode: 'UNDIVIDED',
    text: ['적금 누적 총액'],
    money: ['210,000'],
    unit: '원',
  },
};
