import type { Meta, StoryObj } from '@storybook/react';

import OtherInfoElement from './OtherInfoElement';

const meta = {
  title: 'Common/OtherInfoElement',
  component: OtherInfoElement,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {},
} satisfies Meta<typeof OtherInfoElement>;

export default meta;
type Story = StoryObj<typeof meta>;

export const MyStock: Story = {
  args: {
    imageSrc: 'http://localhost:3000/images/icon-dollar.png',
    leftExplainText: '2주',
    leftHighlightText: '우산 회사',
    rightExplainText: '-205,400(-53.2%)',
    rightHighlightText: '181,000 자스민',
    state: 'DOWN',
  },
};

export const CompanyStock: Story = {
  args: {
    imageSrc: 'http://localhost:3000/images/icon-dollar.png',
    leftExplainText: '거래량 3,210주',
    leftHighlightText: '눈오리 회사',
    rightExplainText: '-2,400(-13.2%)',
    rightHighlightText: '20,460 자스민',
    state: 'DOWN',
  },
};

export const Book: Story = {
  args: {
    imageSrc: 'http://localhost:3000/images/icon-dollar.png',
    leftExplainText: '계좌이체',
    leftHighlightText: '윤진아',
    state: 'UP',
    money: '8,000 원',
  },
};
